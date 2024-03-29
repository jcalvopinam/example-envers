/*
 * MIT License
 *
 * Copyright (c) 2022 JUAN CALVOPINA M
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

package com.jcalvopinam.service;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;

import java.util.List;

/**
 * @author Juan Calvopina
 */
public interface PersonService {

    /**
     * Retrieves all persons from database.
     *
     * @return a list of People.
     */
    List<Person> findAll();

    /**
     * Finds the person by name or last name.
     *
     * @param id       receives the id to be filtered.
     * @param name     receives the `name` to be filtered.
     * @param lastName receives the lastName to be filtered.
     *
     * @return a Person filtered by id, name or lastName.
     */
    Person findByText(String id, String name, String lastName);

    /**
     * Finds the person by id.
     *
     * @param id receives the id to be filtered.
     *
     * @return a Person filtered by id.
     */
    Person findById(Long id);

    /**
     * Adds a new person to the database.
     *
     * @param personDTO receives the personDTO to be saved.
     *
     * @return the Person saved.
     */
    Person save(PersonDTO personDTO);

    /**
     * Updates a person to the database.
     *
     * @param personDTO receives the personDTO to be updated.
     * @param id        receives the id to be filtered.
     *
     * @return the Person updated.
     */
    Person update(PersonDTO personDTO, final Long id);

    /**
     * Deletes a person by Id from database
     *
     * @param id receives the id to be deleted.
     */
    void deleteById(Long id);

}
