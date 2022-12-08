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

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;
import com.jcalvopinam.exception.AlreadyExistsException;
import com.jcalvopinam.exception.NotFoundException;
import com.jcalvopinam.repository.PersonRepository;
import com.jcalvopinam.utils.Utilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Juan Calvopina <juan.calvopina@gmail.com>
 */
@ExtendWith(SpringExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonRepository personRepository;

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
        Mockito.when(personRepository.findByIdOrFirstNameOrLastName(1L, null, null))
               .thenReturn(getPerson());
        final Person byText = this.personService.findByText("1", null, null);
        Assertions.assertNotNull(byText.getId(), "The id is null");
    }

    @Test
    void findByText_name() {
        Mockito.when(personRepository.findByIdOrFirstNameOrLastName(0L, "Something", null))
               .thenReturn(getPerson());
        final Person byText = this.personService.findByText("0", "Something", null);
        Assertions.assertNotNull(byText.getFirstName(), "The name is null");
    }

    @Test
    void findByText_lastName() {
        Mockito.when(personRepository.findByIdOrFirstNameOrLastName(0L, null, "Something"))
               .thenReturn(getPerson());
        final Person byText = this.personService.findByText("0", null, "Something");
        Assertions.assertNotNull(byText.getLastName(), "The lastName is null");
    }

    @Test
    void findById() {
        Mockito.when(personRepository.findById(0L))
               .thenReturn(getOptionalPerson());
        final Person personFound = this.personService.findById(0L);
        Assertions.assertEquals(0L, personFound.getId(), "The is is null");
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

        final Person person = new Person(personDTO);

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

        Mockito.when(personRepository.findById(0L))
               .thenReturn(getOptionalPerson());

        if (getOptionalPerson().isEmpty()) {
            Assertions.fail();
        }

        final Person person = getOptionalPerson().get();

        Mockito.when(personRepository.save(Mockito.any()))
               .thenReturn(person);

        final Person personSaved = personService.update(personDTO, 0L);
        Assertions.assertNotNull(personSaved.getFirstName(), "The id is null");
    }

    @Test
    void deleteById() {
        Mockito.when(personRepository.findById(0L))
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

    private Optional<Person> getOptionalPerson() {
        return this.getPeople()
                   .stream()
                   .findFirst();
    }

    private Person getPerson() {
        final Optional<Person> person = this.getOptionalPerson();
        return person.isPresent() ? person.get() : new Person();
    }

    private List<Person> getPeople() {
        return IntStream.range(0, 5)
                        .mapToObj(i -> Person.builder()
                                             .id((long) i)
                                             .firstName(Utilities.getRandomBy(Utilities.NAMES))
                                             .lastName(Utilities.getRandomBy(Utilities.LASTNAMES))
                                             .build())
                        .collect(Collectors.toCollection(() -> new ArrayList<>(5)));
    }

    private PersonDTO getPersonDTO() {
        return new PersonDTO(getPerson().getId(), getPerson().getFirstName(), getPerson().getLastName());
    }

}
