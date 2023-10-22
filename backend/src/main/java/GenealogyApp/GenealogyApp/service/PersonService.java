package GenealogyApp.GenealogyApp.service;

import GenealogyApp.GenealogyApp.dao.PersonRepository;
import GenealogyApp.GenealogyApp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService
{
    private PersonRepository personRepository;


    @Autowired
    public PersonService(PersonRepository personRepository)
    {
        this.personRepository = personRepository;
    }
    @Transactional
    public void save(Person person)
    {
        personRepository.save(person);
    }
    public Person findById(int id)
    {
        return personRepository.findById(id).orElse(null);
    }
    @Transactional
    public void delete(int id)
    {
        Person tempPerson = findById(id);

        tempPerson.setMother(null);
        tempPerson.setFather(null);

        save(tempPerson);

        personRepository.deleteById(id);
    }
    public List<Person> findByParents(int motherId, int fatherId)
    {
        List<Person> children = personRepository.findByParents(motherId, fatherId);

        return  children;
    }
    public List<Person> findAll()
    {
        List<Person> personList = personRepository.findAll();

        return personList;
    }
    public List<Person> findByNameAndSurnameLike(String name, String surname)
    {
        List<Person> personList = personRepository.findByNameAndSurnameLike(name, surname);

        return personList;
    }
}
