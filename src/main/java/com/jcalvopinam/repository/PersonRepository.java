/**
 * Methods for accessing the database
 */
package com.jcalvopinam.repository;

import com.jcalvopinam.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

    /**
     * Finds the person by name or last name
     *
     * @param id
     * @param name
     * @param lastName
     * @return
     */
    Person findByIdOrFirstNameOrLastName(int id, String name, String lastName);

}
