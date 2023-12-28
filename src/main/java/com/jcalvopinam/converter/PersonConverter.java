package com.jcalvopinam.converter;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

    private final ModelMapper modelMapper;

    public PersonConverter(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Person fromDTOtoPerson(final PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    public Person fromDTOtoPerson(final PersonDTO personDTO, final Person person) {
        Person mapped = modelMapper.map(personDTO, Person.class);
        mapped.setId(person.getId());
        return mapped;
    }

}
