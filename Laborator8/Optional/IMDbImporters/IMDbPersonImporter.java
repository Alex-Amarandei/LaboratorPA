package optional.IMDbImporters;

import com.opencsv.bean.CsvToBeanBuilder;
import optional.models.Movie;
import optional.models.Person;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class IMDbPersonImporter {
    public List<Person> getIMDbPersonList() {
        List<Person> persons = new ArrayList<>();
        try {
             persons = new CsvToBeanBuilder(
                    new FileReader("/Users/alex-ama/Files/Year 2 Courses/Second Semester/Java/Laborator8/src/main/resources/IMDb names.csv")
            ).withSkipLines(1)
                     .withType(Person.class)
                     .withThrowExceptions(false)
                     .build()
                     .parse();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        finally {
            return persons;
        }
    }
}
