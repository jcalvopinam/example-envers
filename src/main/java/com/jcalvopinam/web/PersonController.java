/**
 * Endpoint for access to the services
 */
package com.jcalvopinam.web;

import com.jcalvopinam.domain.Person;
import com.jcalvopinam.dto.PersonDTO;
import com.jcalvopinam.service.PersonServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/")
    public String init() {
        return "Welcome to Envers example";
    }

    @RequestMapping(value = "/all-persons", method = RequestMethod.GET)
    public List<Person> findAllPersons() {
        logger.info("Find all persons");
        return personService.findAll();
    }

    @RequestMapping(value = "/find-by-person", method = RequestMethod.GET)
    public Person findByText(@RequestParam(value = "text", required = true) String text) {
        logger.info(String.format("Finding by: %s", text));
        int id = 0;
        if (StringUtils.isNumeric(text)) {
            id = Integer.parseInt(text);
        }
        return personService.findByText(id, text, text);
    }

    @RequestMapping(value = "/save-person", method = RequestMethod.POST)
    public String savePerson(@RequestBody PersonDTO personDTO) {
        Validate.notNull(personDTO, "The person cannot be null");
        logger.info(String.format("Saving person: %s", personDTO.toString()));
        return personService.save(personDTO);
    }

    @RequestMapping(value = "/update-person", method = RequestMethod.POST)
    public String updatePerson(@RequestBody PersonDTO personDTO) {
        Validate.notNull(personDTO, "The person cannot be null");
        logger.info(String.format("Updating person: %s", personDTO.toString()));
        return personService.update(personDTO);
    }

    @RequestMapping(value = "/delete-person", method = RequestMethod.GET)
    public String deletePerson(@RequestParam(value = "id", required = true) int id) {
        logger.info(String.format("Deleting person: %s", id));
        return personService.deleteById(id);
    }

}
