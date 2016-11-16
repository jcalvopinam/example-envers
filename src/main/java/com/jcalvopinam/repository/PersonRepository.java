/**
 * Methods for accessing the database
 */
package com.jcalvopinam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jcalvopinam.domain.Person;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

    /**
     * Finds the person by name or last name
     * 
     * @param name
     * @param lastName
     * @return
     */
    public Person findByFirstNameOrLastName(String name, String lastName);

}
