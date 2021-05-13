package compulsory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Person {
    private int id;
    private String username;
    private String password;

    public static List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();

        try {
            Statement statement = Database.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM person");
            Person person;
            while(rs.next()) {
                person = new Person(rs.getInt(1), rs.getString(2), rs.getString(3));
                persons.add(person);
            }
            statement.close();
            rs.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    public static boolean addPerson(Person person) {
        boolean returnVal = false;

        try {
            PreparedStatement preparedStatement = Database.getConnection()
                    .prepareStatement("INSERT INTO person VALUES(?, ?, ?)");
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getUsername());
            preparedStatement.setString(3, person.getPassword());
            if(preparedStatement.executeUpdate() != 0)
                returnVal = true;
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return returnVal;
    }

    public static boolean changePersonUsername(Person person, int id) {
        boolean returnVal = false;

        try {
            PreparedStatement preparedStatement = Database.getConnection()
                    .prepareStatement("UPDATE person SET username = ? WHERE id = ?");
            preparedStatement.setString(1, person.getUsername());
            preparedStatement.setInt(2, id);
            if(preparedStatement.executeUpdate() != 0)
                returnVal = true;
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return returnVal;
    }

    public static boolean deletePerson(int id) {
        boolean returnVal = false;

        try {
            PreparedStatement preparedStatement = Database.getConnection()
                    .prepareStatement("DELETE FROM person WHERE id = ?");
            preparedStatement.setInt(1, id);
            if(preparedStatement.executeUpdate() != 0)
                returnVal = true;
            preparedStatement.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return returnVal;
    }

    public Person() {
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Person(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
