package GenealogyApp.GenealogyApp.dto;

import java.time.LocalDate;

public class PersonDTO
{
    private String name;
    private String surname;
    private int fatherId;
    private int motherId;
    private LocalDate birth;
    private LocalDate death;

    public PersonDTO(String name, String surname, int fatherId, int motherId, LocalDate birth, LocalDate death)
    {
        this.name = name;
        this.surname = surname;
        this.fatherId = fatherId;
        this.motherId = motherId;
        this.birth = birth;
        this.death = death;
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

    public int getFatherId()
    {
        return fatherId;
    }

    public void setFatherId(int fatherId)
    {
        this.fatherId = fatherId;
    }

    public int getMotherId()
    {
        return motherId;
    }

    public void setMotherId(int motherId)
    {
        this.motherId = motherId;
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
}
