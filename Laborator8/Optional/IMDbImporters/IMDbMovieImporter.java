package optional.IMDbImporters;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.opencsv.exceptions.CsvException;
import optional.models.Movie;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class IMDbMovieImporter {
    public List<Movie> getIMDbMovieList() {
        List<Movie> movies = new ArrayList<>();
        try {
             movies = new CsvToBeanBuilder(
                    new FileReader("/Users/alex-ama/Files/Year 2 Courses/Second Semester/Java/Laborator8/src/main/resources/IMDb movies.csv")
            ).withSkipLines(1)
                     .withType(Movie.class)
                     .withThrowExceptions(false)
                     .build()
                     .parse();
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        finally {
            return movies;
        }
    }
}
