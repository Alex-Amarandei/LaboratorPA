package optional.models;

public class Genre {
    private Integer id;
    private String name;

    public Genre() {
        this.id = -1;
        this.name = "";
    }

    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Genre: " + name;
    }
}
