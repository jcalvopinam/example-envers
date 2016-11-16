/**
 * Implementation of the method signatures
 */
package com.jcalvopinam.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;
import com.jcalvopinam.repository.PersonRepository;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        for (Person person : personRepository.findAll()) {
            persons.add(person);
        }
        return persons;
    }

    @Override
    public Person findByText(String name, String lastName) {
        return personRepository.findByFirstNameOrLastName(name, lastName);
    }

    @Override
    public String save(PersonDTO personDTO) {
        Person person = new Person(personDTO.getName(), personDTO.getLastName());
        personRepository.save(person);
        return "Person saved!";
    }

    @Override
    public String deleteById(int id) {
        personRepository.delete(id);
        return "Person deleted!";
    }

}
