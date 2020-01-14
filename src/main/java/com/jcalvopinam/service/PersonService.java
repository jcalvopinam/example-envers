/*
 * MIT License
 *
 * Copyright (c) 2017 JUAN CALVOPINA M
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
     * Retrieves all persons from database.
     *
     * @return a List of Person.
     */
    List<Person> findAll();

    /**
     * Finds the person by name or last name.
     *
     * @param id       receive an id value.
     * @param name     receive a name value.
     * @param lastName receive a lastName value.
     * @return a Person object.
     */
    Person findByText(String id, String name, String lastName);

    /**
     * Finds the person by int.
     *
     * @param id receive an id value.
     * @return a Person Object.
     */
    Person findById(int id);

    //FIXME: Update those methods.

    /**
     * Adds a new person to the database.
     *
     * @return
     */
    String save(PersonDTO personDTO);

    /**
     * Updates a person to the database.
     *
     * @return
     */
    String update(PersonDTO personDTO);

    /**
     * Deletes a person by Id from database.
     *
     * @return
     */
    String deleteById(int id);

}
