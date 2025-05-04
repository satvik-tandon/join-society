package edu.dbms.joinsociety.controller.customer;

import edu.dbms.joinsociety.dto.CustomerDTO;
import edu.dbms.joinsociety.dto.CustomerLoginDTO;
import edu.dbms.joinsociety.dto.ErrorDTO;
import edu.dbms.joinsociety.service.customer.CustomerService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {
    @Resource(name = "customerService")
    private CustomerService customerService;

    @Autowired
    private HttpSession httpSession;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody CustomerLoginDTO loginDTO) {
        Optional<CustomerDTO> customerDTO = customerService.doLogin(loginDTO);
        if (customerDTO.isPresent()) {
            httpSession.setAttribute("loggedInUser", loginDTO.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
        }

        ErrorDTO error = new ErrorDTO("Login failed.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
