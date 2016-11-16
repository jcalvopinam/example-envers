/**
 * Endpoint for access to the services
 */
package com.jcalvopinam.web;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;
import com.jcalvopinam.service.PersonServiceImpl;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/")
    public String init() {
        return "Welcome to Envers example";
    }

    @RequestMapping(value = "/all-persons", method = RequestMethod.GET)
    public String allPersons() {
        return personService.findAll()
                            .toString();
    }

    @RequestMapping(value = "/find-by-person", method = RequestMethod.GET)
    public Person findByText(@RequestParam(value = "text", required = true) String text) {
        return personService.findByText(text, text);
    }

    @RequestMapping(value = "/save-person", method = RequestMethod.POST)
    public String savePerson(@RequestBody PersonDTO person) {
        Validate.notNull(person, "The person cannot be null");
        return personService.save(person);
    }

    @RequestMapping(value = "/delete-person", method = RequestMethod.GET)
    public String deletePerson(@RequestParam(value = "id", required = true) int id) {
        return personService.deleteById(id);
    }

}
