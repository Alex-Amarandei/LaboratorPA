package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.ButtonType.OK;

public class Client extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/Menu.fxml"));
            Parent root = fxmlLoader.load();
            Scene menuScene = new Scene(root);
            stage.setScene(menuScene);
            stage.setWidth(720);
            stage.setHeight(800);
            stage.setTitle("FX Backgammon");
            stage.show();

            stage.setOnCloseRequest(event -> {
                event.consume();
                exitMenu(stage);
            });
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void exitMenu(Stage stage) {
        Alert alert = new Alert(CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("You are about to close the app.");
        alert.setContentText("Are you sure you want to quit?");


        File file = new File("src/resources/wallpaper.jpg");
        try {
            ImageView imageView = new ImageView(file.toURI().toURL().toString());
            imageView.setFitWidth(25);
            imageView.setFitHeight(25);
            alert.setGraphic(imageView);
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }

        if (alert.showAndWait().get() == OK) {
            stage.close();
            System.exit(0);
        }
    }
}
