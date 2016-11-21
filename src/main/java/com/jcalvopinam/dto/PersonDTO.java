/**
 * DTO to mapping of Person attributes
 */
package com.jcalvopinam.dto;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public class PersonDTO {

    private int id;
    private String name;
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
