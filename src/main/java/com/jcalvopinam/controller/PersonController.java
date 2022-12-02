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

/*
  Endpoint for access to the controller
 */
package com.jcalvopinam.controller;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;
import com.jcalvopinam.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Juan Calvopina <juan.calvopina@gmail.com>
 */
@RestController
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(final PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAllPeople() {
        LOGGER.info("Find all persons");
        return new ResponseEntity<>(personService.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{text}")
    public ResponseEntity<Person> findByText(@PathVariable final String text) {
        LOGGER.info("Finding by {}", text);
        return new ResponseEntity<>(personService.findByText(text, text, text), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody final PersonDTO personDTO) {
        //TODO: Validate.notNull(personDTO, "The person cannot be null");
        LOGGER.info("Saving person {}", personDTO.toString());
        return new ResponseEntity<>(personService.save(personDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(@RequestBody final PersonDTO personDTO) {
        //TODO: Validate.notNull(personDTO, "The person cannot be null");
        LOGGER.info("Updating person {}", personDTO.toString());
        return new ResponseEntity<>(personService.update(personDTO), HttpStatus.ACCEPTED);
    }

}
