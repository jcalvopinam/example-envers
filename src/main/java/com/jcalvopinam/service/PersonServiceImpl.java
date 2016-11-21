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
        return personRepository.findAll();
    }

    @Override
    public Person findByText(int id, String name, String lastName) {
        return personRepository.findByIdOrFirstNameOrLastName(id, name, lastName);
    }

    @Override
    public String save(PersonDTO personDTO) {
        personRepository.save(new Person(personDTO));
        return "Person saved!";
    }

    @Override
    public String update(PersonDTO personDTO){
        Person person = personRepository.findOne(personDTO.getId());
        person = this.updatePerson(person, personDTO);
        personRepository.save(person);
        return "Person updated!";
    }

    @Override
    public String deleteById(int id) {
        personRepository.delete(id);
        return "Person deleted!";
    }

    private Person updatePerson(Person person, PersonDTO personDTO){
        person.setFirstName(personDTO.getName());
        person.setLastName(personDTO.getLastName());
        return person;
    }

}
