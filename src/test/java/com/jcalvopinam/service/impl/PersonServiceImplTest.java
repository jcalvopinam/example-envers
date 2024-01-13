/*
 * MIT License
 *
 * Copyright (c) 2022 JUAN CALVOPINA M
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

import com.jcalvopinam.controller.BaseControllerTest;
import com.jcalvopinam.converter.PersonConverter;
import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;
import com.jcalvopinam.exception.AlreadyExistsException;
import com.jcalvopinam.exception.NotFoundException;
import com.jcalvopinam.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.jcalvopinam.utils.DummyPerson.getOptionalPerson;
import static com.jcalvopinam.utils.DummyPerson.getPeople;
import static com.jcalvopinam.utils.DummyPerson.getPerson;
import static com.jcalvopinam.utils.DummyPerson.getPersonDTO;

/**
 * @author Juan Calvopina
 */
@ExtendWith(SpringExtension.class)
class PersonServiceImplTest extends BaseControllerTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonConverter personConverter;

    @InjectMocks
    private PersonServiceImpl personService;

    @Test
    void findAll() {
        Mockito.when(personRepository.findAll())
               .thenReturn(getPeople());
        final List<Person> all = this.personService.findAll();
        Assertions.assertEquals(getPeople().size(), all.size(), "It is missing people");
    }


    @Test
    void findByText_id() {
        Mockito.when(personRepository.findByIdOrFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(1L, null, null))
               .thenReturn(getOptionalPerson());
        final Person byText = this.personService.findByText("1", null, null);
        Assertions.assertNotNull(byText.getId(), "The id is null");
    }

    @Test
    void findByText_name() {
        Mockito.when(personRepository.findByIdOrFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(1L, "Something",
                                                                                                        null))
               .thenReturn(getOptionalPerson());
        final Person byText = this.personService.findByText("1", "Something", null);
        Assertions.assertNotNull(byText.getFirstName(), "The name is null");
    }

    @Test
    void findByText_lastName() {
        Mockito.when(personRepository.findByIdOrFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(1L, null,
                                                                                                        "Something"))
               .thenReturn(getOptionalPerson());
        final Person byText = this.personService.findByText("1", null, "Something");
        Assertions.assertNotNull(byText.getLastName(), "The lastName is null");
    }

    @Test
    void findById() {
        Mockito.when(personRepository.findById(1L))
               .thenReturn(getOptionalPerson());
        final Person personFound = this.personService.findById(1L);
        Assertions.assertEquals(1L, personFound.getId(), "The is is null");
    }

    @Test
    void findById_NotFoundException() {
        Mockito.when(personRepository.findById(0L))
               .thenReturn(getOptionalPerson());

        Assertions.assertThrows(NotFoundException.class,
                                () -> personService.findById(1L), "The Person 1 not found");
    }

    @Test
    void save() {
        final PersonDTO personDTO = getPersonDTO();
        personDTO.setId(null);

        Mockito.when(personRepository.findById(personDTO.getId()))
               .thenReturn(Optional.empty());

        Person person = getPerson();
        Mockito.when(personConverter.fromDTOtoPerson(getPersonDTO()))
               .thenReturn(person);

        Mockito.when(personRepository.save(Mockito.any()))
               .thenReturn(person);

        final Person personSaved = personService.save(personDTO);
        Assertions.assertNotNull(personSaved.getFirstName(), "The id is null");
    }

    @Test
    void save_alreadyExistsException() {
        final PersonDTO personDTO = getPersonDTO();

        Mockito.when(personRepository.findById(personDTO.getId()))
               .thenReturn(getOptionalPerson());

        final Person person = getPerson();

        Mockito.when(personRepository.save(Mockito.any()))
               .thenReturn(person);

        Assertions.assertThrows(AlreadyExistsException.class,
                                () -> personService.save(personDTO), "Expected AlreadyExistsException");
    }

    @Test
    void update() {
        final PersonDTO personDTO = getPersonDTO();

        Mockito.when(personRepository.findById(1L))
               .thenReturn(getOptionalPerson());

        if (getOptionalPerson().isEmpty()) {
            Assertions.fail();
        }

        final Person person = getOptionalPerson().get();

        Mockito.when(personRepository.save(Mockito.any()))
               .thenReturn(person);

        final Person personSaved = personService.update(personDTO, 1L);
        Assertions.assertNotNull(personSaved.getFirstName(), "The id is null");
    }

    @Test
    void deleteById() {
        Mockito.when(personRepository.findById(1L))
               .thenReturn(getOptionalPerson());

        if (getOptionalPerson().isEmpty()) {
            Assertions.fail();
        }

        final Person person = getOptionalPerson().get();

        personService.deleteById(person.getId());

        Assertions.assertNotNull(person.getId());

        Mockito.verify(personRepository, Mockito.times(1))
               .deleteById(person.getId());
    }

}
