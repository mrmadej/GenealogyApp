package GenealogyApp.GenealogyApp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Person")
public class Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "birth")
    private LocalDate birth;
    @Column(name = "death")
    private LocalDate death;
    @ManyToOne
    @JoinColumn(name = "mother_id", referencedColumnName = "id")
    private Person mother;
    @ManyToOne
    @JoinColumn(name = "father_id", referencedColumnName = "id")
    private  Person father;

    public Person()
    {

    }

    public Person(String name, String surname, LocalDate birth, LocalDate death, Person mother, Person father)
    {
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.death = death;
        this.mother = mother;
        this.father = father;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public LocalDate getBirth()
    {
        return birth;
    }

    public void setBirth(LocalDate birth)
    {
        this.birth = birth;
    }

    public LocalDate getDeath()
    {
        return death;
    }

    public void setDeath(LocalDate death)
    {
        this.death = death;
    }

    public Person getMother()
    {
        return mother;
    }

    public void setMother(Person mother)
    {
        this.mother = mother;
    }

    public Person getFather()
    {
        return father;
    }

    public void setFather(Person father)
    {
        this.father = father;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                ", death=" + death +
                ", mother=" + mother +
                ", father=" + father +
                '}';
    }
}
