package compulsory.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "persons")
@NamedQueries({
        @NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.actorName LIKE :name")
})
public class Person {
    private String id;
    private String actorName;
    private String realName;
    private Date dateOfBirth;
    private Date dateOfDeath;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "actor_name")
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Basic
    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "date_of_birth")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Basic
    @Column(name = "date_of_death")
    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person that = (Person) o;
        return Objects.equals(id, that.id) && Objects.equals(actorName, that.actorName) && Objects.equals(realName, that.realName) && Objects.equals(dateOfBirth, that.dateOfBirth) && Objects.equals(dateOfDeath, that.dateOfDeath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actorName, realName, dateOfBirth, dateOfDeath);
    }
}
