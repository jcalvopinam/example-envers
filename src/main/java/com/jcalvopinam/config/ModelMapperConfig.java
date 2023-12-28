package com.jcalvopinam.config;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<PersonDTO, Person>() {
            @Override
            protected void configure() {
                map().setFirstName(source.getName());
            }
        });

        return modelMapper;
    }
}