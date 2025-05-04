package edu.dbms.joinsociety.controller.customer;

import edu.dbms.joinsociety.dto.CustomerDTO;
import edu.dbms.joinsociety.dto.CustomerRegisterDTO;
import edu.dbms.joinsociety.dto.ErrorDTO;
import edu.dbms.joinsociety.service.customer.CustomerService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/my-profile")
public class CustomerProfileController {

    @Resource(name = "customerService")
    private CustomerService customerService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable final Long id) {
        Optional<CustomerDTO> customerDTO = customerService.getCustomerById(id);
        if (customerDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(customerDTO.get());
        }

        ErrorDTO error = new ErrorDTO("User not found.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getCustomerProfile() {
        final String userEmail = String.valueOf(httpSession.getAttribute("loggedInUser"));
        if (Objects.nonNull(userEmail)) {
            Optional<CustomerDTO> customerDTO = customerService.getCustomer(userEmail);

            // User not found
            if (customerDTO.isEmpty()) {
                ErrorDTO error = new ErrorDTO("User not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            return ResponseEntity.status(HttpStatus.OK).body(customerDTO.get());
        }

        ErrorDTO error = new ErrorDTO("Email cannot be null.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCustomerProfile(@RequestBody final CustomerRegisterDTO registerDTO) {
        // If customer already exists, throw a conflict
        Optional<CustomerDTO> customer = customerService.getCustomer(registerDTO.getEmail());
        if (customer.isPresent()) {
            ErrorDTO error = new ErrorDTO("Email already in use.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }

        // Otherwise, create one. If error occurs, throw an error response
        boolean created = customerService.createCustomer(registerDTO);
        if (created) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        ErrorDTO error = new ErrorDTO("An error occurred while creating the user.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
