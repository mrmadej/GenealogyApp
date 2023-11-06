package GenealogyApp.GenealogyApp.dao;

import GenealogyApp.GenealogyApp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer>
{
    Optional<Person> findByName(String name);
    Optional<Person> findBySurname(String surname);
    @Query("SELECT p FROM Person p WHERE p.mother.id = :motherId AND p.father.id = :fatherId")
    List<Person> findByParents(
        @Param("motherId") int motherId,
        @Param("fatherId") int fatherId
    );
    @Query("SELECT p FROM Person p WHERE p.name LIKE :name% AND p.surname LIKE :surname%")
    List<Person> findByNameAndSurnameLike(
        @Param("name") String name,
        @Param("surname") String surname);

    @Query("SELECT p FROM Person p WHERE p.father.id = :id OR p.mother.id = :id")
    List<Person> findChildren(
            @Param("id") int id
    );
}
