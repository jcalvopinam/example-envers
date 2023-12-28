/*
 * MIT License
 *
 * Copyright (c) 2023 JUAN CALVOPINA M
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

package com.jcalvopinam.service.impl;

import com.jcalvopinam.converter.PersonConverter;
import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;
import com.jcalvopinam.exception.AlreadyExistsException;
import com.jcalvopinam.exception.NotFoundException;
import com.jcalvopinam.repository.PersonRepository;
import com.jcalvopinam.service.PersonService;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Juan Calvopina
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    public PersonServiceImpl(final PersonRepository personRepository, PersonConverter personConverter) {
        this.personRepository = personRepository;
        this.personConverter = personConverter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Person> findAll() {
        LOGGER.info("Getting all people");
        return (List<Person>) personRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person findByText(final String id, final String name, final String lastName) {
        LOGGER.info("Finding by {} or {} or {}", id, name, lastName);
        final Long personId = NumberUtils.createLong(id);
        return personRepository.findByIdOrFirstNameOrLastName(personId, name, lastName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person findById(final Long id) {
        LOGGER.info("Finding by {}", id);
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("The Person %s not found", id)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person save(final PersonDTO personDTO) {
        if (personRepository.findById(personDTO.getId())
                            .isPresent()) {
            final String message = String.format("The Person %s already exist", personDTO.getId());
            LOGGER.error(message);
            throw new AlreadyExistsException(message);
        }
        LOGGER.info("Saving new person {} {}", personDTO.getName(), personDTO.getLastName());
        final Person person = personConverter.fromDTOtoPerson(personDTO);
        return personRepository.save(person);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person update(final PersonDTO personDTO, final Long id) {
        final Person personFound = this.findById(id);
        final Person person = personConverter.fromDTOtoPerson(personDTO, personFound);
        LOGGER.info("Updating the person {}", personDTO.getName());
        return personRepository.save(person);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(final Long id) {
        final Person person = this.findById(id);
        LOGGER.info("Deleting the person {}", person.getId());
        personRepository.deleteById(person.getId());
    }

}
