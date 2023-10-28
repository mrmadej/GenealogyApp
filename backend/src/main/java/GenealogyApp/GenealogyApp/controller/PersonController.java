package GenealogyApp.GenealogyApp.controller;

import GenealogyApp.GenealogyApp.dto.PersonDTO;
import GenealogyApp.GenealogyApp.entity.Person;
import GenealogyApp.GenealogyApp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Main")
public class PersonController
{
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService)
    {
        this.personService = personService;
    }

    @GetMapping("/people")
    public List<Person> getPersons()
    {
        System.out.println("Jestem");
        return personService.findAll();
    }
    @PostMapping("/savePerson")
    ResponseEntity<String> submitFormAddPerson(@RequestBody PersonDTO personDTO)
    {

        String name = personDTO.getName();
        String surname = personDTO.getSurname();
        LocalDate birth = personDTO.getBirth();
        LocalDate death = personDTO.getDeath();
        int motherId = personDTO.getMotherId();
        int fatherId = personDTO.getFatherId();

        Person mother = personService.findById(motherId);
        Person father = personService.findById(fatherId);

        Person tempPerson = new Person(name, surname, birth, death, mother, father);
        System.out.println(personDTO.getName());
        System.out.println(personDTO.getId());
        System.out.println(mother);
        System.out.println(father);
        System.out.println(motherId);
        System.out.println(fatherId);
        if(personDTO.getId() > 0)
        {
            tempPerson.setId(personDTO.getId());
        }

        personService.save(tempPerson);
        return null;
    }
    @GetMapping("/findPersonByNameAndSurnameLike")
    public ResponseEntity<List<Person>> findPersonByNameAndSurnameLike(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname
    )
    {
        List<Person> personList = personService.findByNameAndSurnameLike(name, surname);

        return ResponseEntity.ok(personList);
    }
}
