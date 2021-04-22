package optional.dao;

import optional.database.DatabaseConnection;
import optional.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    public void create(String id, String actorName, String realName, Date dateOfBirth, Date dateOfDeath) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO persons VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, actorName);
            preparedStatement.setString(3, realName);
            preparedStatement.setDate(4, dateOfBirth);
            preparedStatement.setDate(5, dateOfDeath);
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate entry. Insert operation cancelled.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void create(Person person) {
        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("INSERT INTO persons VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, person.getId());
            preparedStatement.setString(2, person.getActorName());
            preparedStatement.setString(3, person.getRealName());
            preparedStatement.setDate(4, person.getDateOfBirth());
            preparedStatement.setDate(5, person.getDateOfDeath());
            preparedStatement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate entry. Insert operation cancelled.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Person getById(String id) {
        Person person = new Person();

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM persons WHERE id = ?")) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            person = new Person(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4),
                    resultSet.getDate(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    public List<Person> getByName(String name) {
        List<Person> persons = new ArrayList<>();

        try (PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM persons WHERE actor_name = ?")) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                persons.add(
                        new Person(resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDate(4),
                                resultSet.getDate(5)
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }
}
