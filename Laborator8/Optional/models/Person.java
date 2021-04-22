package optional.models;

import com.opencsv.bean.CsvBindByPosition;

import java.sql.Date;
import java.time.LocalDate;

public class Person {
    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private String actorName;

    @CsvBindByPosition(position = 2)
    private String realName;

    @CsvBindByPosition(position = 6)
    private Date dateOfBirth;

    @CsvBindByPosition(position = 9)
    private Date dateOfDeath;

    public Person() {
        this.id = "id";
        this.actorName = "actorName";
        this.realName = "realName";
        this.dateOfBirth = Date.valueOf(LocalDate.now());
        this.dateOfDeath = Date.valueOf(LocalDate.now());
    }

    public Person(String id, String actorName, String realName, Date dateOfBirth, Date dateOfDeath) {
        this.id = id;
        this.actorName = actorName;
        this.realName = realName;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(Date dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    @Override
    public String toString() {
        return actorName + " (" +
                realName + ") - " +
                dateOfBirth + "\n";
    }
}
