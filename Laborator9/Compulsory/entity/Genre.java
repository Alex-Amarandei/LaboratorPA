package compulsory.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "genres")
@NamedQueries({
        @NamedQuery(name = "Genre.findByName", query = "SELECT g FROM Genre g WHERE g.name LIKE :name")
})
public class Genre {
    private Integer id;
    private String name;

    @Basic
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre that = (Genre) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
