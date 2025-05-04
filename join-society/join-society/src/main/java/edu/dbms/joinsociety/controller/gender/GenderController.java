package edu.dbms.joinsociety.controller.gender;


import edu.dbms.joinsociety.dto.GenderDTO;
import edu.dbms.joinsociety.service.gender.GenderService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genders")
public class GenderController {

    @Resource(name = "genderService")
    private GenderService genderService;

    @GetMapping
    public ResponseEntity<Object> getGenders() {
        List<GenderDTO> genders = genderService.getGenders();
        return ResponseEntity.status(HttpStatus.OK).body(genders);
    }
}
