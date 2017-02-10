/**
 * List of methods signature
 */
package com.jcalvopinam.service;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;

import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface PersonService {

    /**
     * Retrieves all persons from database
     *
     * @return
     */
    List<Person> findAll();

    /**
     * Finds the person by name or last name
     *
     * @param id
     * @param name
     * @param lastName
     * @return
     */
    Person findByText(String id, String name, String lastName);

    /**
     * Finds the person by int
     * @param id
     * @return
     */
    Person findById(int id);

    /**
     * Adds a new person to the database
     *
     * @param personDTO
     * @return
     */
    String save(PersonDTO personDTO);

    /**
     * Updates a person to the database
     *
     * @param personDTO
     * @return
     */
    String update(PersonDTO personDTO);

    /**
     * Deletes a person by Id from database
     *
     * @param id
     * @return
     */
    String deleteById(int id);

}
