/**
 * Implementation of the method signatures
 */
package com.jcalvopinam.service;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;
import com.jcalvopinam.repository.PersonRepository;
import com.jcalvopinam.utils.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    private String response;
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findByText(String id, String name, String lastName) {
        Integer personId = Utilities.isInteger(id);
        return personRepository.findByIdOrFirstNameOrLastName(personId,name,lastName);
    }

    @Override
    public Person findById(int id) {
        return personRepository.findOne(id);
    }

    @Override
    public String save(PersonDTO personDTO) {
        response = "Person saved!";
        personRepository.save(new Person(personDTO));
        logger.info(response);
        return response;
    }

    @Override
    public String update(PersonDTO personDTO) {
        response = "Person updated!";
        Person person = personRepository.findOne(personDTO.getId());
        person = this.updatePerson(person, personDTO);
        personRepository.save(person);
        logger.info(response);
        return response;
    }

    @Override
    public String deleteById(int id) {
        response = "Person deleted!";
        personRepository.delete(id);
        logger.info(response);
        return response;
    }

    private Person updatePerson(Person person, PersonDTO personDTO) {
        person.setFirstName(personDTO.getName());
        person.setLastName(personDTO.getLastName());
        return person;
    }

}
