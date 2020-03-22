/*
 * MIT License
 *
 * Copyright (c) 2017 JUAN CALVOPINA M
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.jcalvopinam.service;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonRequestDTO;
import com.jcalvopinam.dto.PersonResponseDTO;
import com.jcalvopinam.exception.PersonConflictException;
import com.jcalvopinam.exception.PersonNotFoundException;
import com.jcalvopinam.repository.PersonRepository;
import com.jcalvopinam.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * Implementation of the method signatures
 *
 * @author juan.calvopina
 */
@Service
@Transactional
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final ConversionService conversionService;

    @Autowired
    public PersonServiceImpl(final PersonRepository personRepository, final ConversionService conversionService) {
        this.personRepository = personRepository;
        this.conversionService = conversionService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonResponseDTO> findByText(String id, String name, String lastName) {
        Integer personId = Utilities.isInteger(id);
        final List<Person> personSourceList =
                personRepository.findByIdOrFirstNameStartingWithOrLastNameStartingWith(personId, name, lastName);

        if (personSourceList.isEmpty()) {
            final String message = "There was no data found";
            log.info(message);
            throw new PersonNotFoundException(message);
        }

        final TypeDescriptor personSourceType =
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Person.class));

        final TypeDescriptor personResponseDTOTargetType =
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(PersonResponseDTO.class));

        return (List<PersonResponseDTO>) conversionService.convert(personSourceList, personSourceType,
                                                                   personResponseDTOTargetType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonRequestDTO findById(int id) {
        return conversionService.convert(findPersonById(id), PersonRequestDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonResponseDTO save(PersonRequestDTO personRequestDTO) {
        Validate.notNull(personRequestDTO, "The person cannot be null");
        personRepository.findByFirstNameAndLastName(personRequestDTO.getFirstName(), personRequestDTO.getLastName())
                        .ifPresent(per -> {
                            final String message = String.format("The person %s %s already exist in the database",
                                                                 per.getFirstName(), per.getLastName());
                            log.info(message);
                            throw new PersonConflictException(message);
                        });
        final Person dtoToPerson = conversionService.convert(personRequestDTO, Person.class);
        final Person person = personRepository.save(Objects.requireNonNull(dtoToPerson));
        log.info("The person [{}] was saved!", person.toString());
        return conversionService.convert(person, PersonResponseDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonResponseDTO update(PersonRequestDTO personRequestDTO, final int id) {
        Validate.notNull(personRequestDTO, "The person cannot be null");
        final Person existingPerson = findPersonById(id);
        final Person personConverted = conversionService.convert(personRequestDTO, existingPerson.getClass());
        personConverted.setId(id);
        final Person person = personRepository.save(Objects.requireNonNull(personConverted));
        log.info("The person [{}] was updated!", person.toString());
        return conversionService.convert(person, PersonResponseDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(int id) {
        final Person person = findPersonById(id);
        personRepository.delete(person);
        log.info("The person [{}] was deleted!", person.toString());
    }

    /**
     * Finds the person by {@code id}.
     *
     * @param id receive an id.
     * @return a Person object.
     */
    private Person findPersonById(final int id) {
        return personRepository.findById(id)
                               .orElseThrow(() -> {
                                   final String message = String.format("The person's id [%d] was not found!", id);
                                   log.error(message);
                                   return new PersonNotFoundException(message);
                               });
    }

}
