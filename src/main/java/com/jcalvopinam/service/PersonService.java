/**
 * List of methods signature
 */
package com.jcalvopinam.service;

import java.util.List;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface PersonService {

    /**
     * Retrieves all persons from database
     * 
     * @return
     */
    public List<Person> findAll();

    /**
     * Finds the person by name or last name
     * 
     * @param name
     * @param lastName
     * @return
     */
    public Person findByText(String name, String lastName);

    /**
     * Adds a new person to the database
     * 
     * @param personDTO
     * @return
     */
    public String save(PersonDTO personDTO);

    /**
     * Deletes a person by Id from database
     * 
     * @param id
     * @return
     */
    public String deleteById(int id);

}
