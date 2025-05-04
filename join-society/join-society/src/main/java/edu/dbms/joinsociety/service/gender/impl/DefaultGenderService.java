package edu.dbms.joinsociety.service.gender.impl;

import edu.dbms.joinsociety.convertors.impl.GenderConvertor;
import edu.dbms.joinsociety.dto.GenderDTO;
import edu.dbms.joinsociety.models.Gender;
import edu.dbms.joinsociety.repositories.GenderRepository;
import edu.dbms.joinsociety.service.gender.GenderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("genderService")
public class DefaultGenderService implements GenderService {

    @Resource
    private GenderRepository genderRepository;

    @Override
    public List<GenderDTO> getGenders() {
        List<Gender> genders = genderRepository.findAll();
        return genders.stream().map(GenderConvertor::toDTO).toList();
    }
}
