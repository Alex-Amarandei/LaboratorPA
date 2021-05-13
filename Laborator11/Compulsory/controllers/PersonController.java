package compulsory.controllers;

import compulsory.Person;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @GetMapping("/persons")
    public List<Person> persons() {
        return Person.getPersons();
    }

    @PostMapping("/persons")
    public boolean addPerson(@RequestBody Person person) {
        return Person.addPerson(person);
    }

    @PutMapping("/persons/{id}")
    public boolean changePersonUsername(@RequestBody Person person, @PathVariable int id) {
        return Person.changePersonUsername(person, id);
    }

    @DeleteMapping("/persons/{id}")
    public boolean deletePerson(@PathVariable int id) {
        return Person.deletePerson(id);
    }
}
