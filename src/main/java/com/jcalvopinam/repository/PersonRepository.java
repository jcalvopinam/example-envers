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

package com.jcalvopinam.repository;

import com.jcalvopinam.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Juan Calvopina
 */
public interface PersonRepository extends CrudRepository<Person, Long> {

    /**
     * Finds the person by id, name or last name
     *
     * @param id       receives the id to be filtered.
     * @param name     receives the name to be filtered.
     * @param lastName receives the lastName to be filtered.
     *
     * @return the Person object.
     */
    Optional<Person> findByIdOrFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(Long id, String name,
                                                                                       String lastName);

}
