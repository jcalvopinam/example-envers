/**
 *
 */
package com.jcalvopinam.domain;

import com.jcalvopinam.dto.PersonDTO;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Entity
@Audited
@Table(name = "PERSON")
public class Person implements Serializable {

    private static final long serialVersionUID = -1932398775322712810L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Person() {
    }

    /**
     * Builds the Person object through of Constructor
     *
     * @param firstName
     * @param lastName
     */
    public Person(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Builds the Person object through of PersonDTO
     *
     * @param personDTO
     */
    public Person(PersonDTO personDTO) {
        this.firstName = personDTO.getName();
        this.lastName = personDTO.getLastName();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
