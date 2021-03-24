/**
 * @author Alex Amarandei
 */

package optional.commands;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import optional.Catalog;
import optional.exceptions.CatalogException;
import optional.exceptions.InvalidNumberOfArgumentsException;
import optional.exceptions.NoCatalogSelectedException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportCommand extends Command {
    public ReportCommand(String commandLine) {
        super(commandLine);
    }

    static void configureTemplate(Catalog catalog) throws IOException {
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading
                (new File("/Users/alex-ama/Desktop/Year 2 Courses/Second Semester/laborator5/src/main/resources"));

        Template template = configuration.getTemplate("Catalog.ftl");

        BufferedWriter writer = new BufferedWriter
                (new FileWriter("/Users/alex-ama/Desktop/Year 2 Courses/Second Semester/laborator5/src/main/resources/report.html"));

        try {
            template.process(catalog, writer);
        } catch (TemplateException e) {
            System.out.println("Error in FreeMarker template.");
            e.printStackTrace();
        }
    }

    @Override
    public Catalog execute(Catalog catalog) throws CatalogException, IOException {
        if (arguments.size() != 1) throw new
                InvalidNumberOfArgumentsException("Too many arguments for Report Command, the expected number is 1.");

        if (catalog == null) throw new
                NoCatalogSelectedException("No catalog selected.");

        configureTemplate(catalog);

        System.out.println("Generated report");
        return catalog;
    }
}
