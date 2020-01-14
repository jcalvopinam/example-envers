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
import com.jcalvopinam.dto.PersonDTO;
import com.jcalvopinam.exception.PersonException;
import com.jcalvopinam.repository.PersonRepository;
import com.jcalvopinam.utils.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of the method signatures
 *
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Service
@Transactional
@Slf4j
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private String response;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
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
    public Person findByText(String id, String name, String lastName) {
        Integer personId = Utilities.isInteger(id);
        return personRepository.findByIdOrFirstNameOrLastName(personId, name, lastName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Person findById(int id) {
        return personRepository.findById(id)
                               .orElseThrow(() -> {
                                   final String message = "Order detail not found!";
                                   log.error(message);
                                   throw new PersonException(message);
                               });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String save(PersonDTO personDTO) {
        response = "Person saved!";
        personRepository.save(new Person(personDTO));
        log.info(response);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String update(PersonDTO personDTO) {
        response = "Person updated!";
        Person person = findById(personDTO.getId());
        person = this.updatePerson(person, personDTO);
        personRepository.save(person);
        log.info(response);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String deleteById(int id) {
        response = "Person deleted!";
        personRepository.deleteById(id);
        log.info(response);
        return response;
    }

    private Person updatePerson(Person person, PersonDTO personDTO) {
        person.setFirstName(personDTO.getName());
        person.setLastName(personDTO.getLastName());
        return person;
    }

}
