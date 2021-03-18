/**
 * @author Alex Amarandei
 */

package compulsory;

public class Main {
    public static void main(String args[]) {
        Main app = new Main();
        app.testCreateSave();
        app.testLoadView();
    }

    private void testCreateSave() {
        Catalog catalog = new Catalog("Favorites", "/Users/alex-ama/Desktop/Year 2 Courses/Second Semester/Java/src/main/resources/catalog.ser");
        var testSong = new Song("TestSong", "/Users/alex-ama/Desktop/Year 2 Courses/Second Semester/Java/src/main/resources/TestSong.mp3");
        var testBook1 = new Book("TestBook1", "/Users/alex-ama/Desktop/Year 2 Courses/Second Semester/Java/src/main/resources/TestBook1.pdf");
        var testBook2 = new Book("TestBook2", "/Users/alex-ama/Desktop/Year 2 Courses/Second Semester/Java/src/main/resources/TestBook2.pdf");
        catalog.add(testSong);
        catalog.add(testBook1);
        catalog.add(testBook2);
        CatalogUtil.save(catalog);
    }

    private void testLoadView() {
        Catalog catalog = CatalogUtil.load("/Users/alex-ama/Desktop/Year 2 Courses/Second Semester/Java/src/main/resources/catalog.ser");
        catalog.play(catalog.findByName("TestSong"));
        catalog.play(catalog.findByName("TestBook1"));
    }
}
