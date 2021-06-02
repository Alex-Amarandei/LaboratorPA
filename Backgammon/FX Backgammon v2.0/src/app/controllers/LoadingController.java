package app.controllers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LoadingController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void searchGameRoom(BufferedReader fromServer, PrintWriter toServer, Stage stage) throws IOException {
        String[] gameRoomData = fromServer.readLine().split("&");
        String whiteUsername = gameRoomData[0];
        String blackUsername = gameRoomData[1];
        int roomNumber = Integer.parseInt(gameRoomData[2]);
        String playerType = gameRoomData[3];

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Game.fxml"));
        root = fxmlLoader.load();
        GameController gameController = fxmlLoader.getController();
        gameController.startGame(fromServer, toServer, roomNumber, whiteUsername, blackUsername, playerType);

        Platform.runLater(() -> {
            this.stage = stage;
            scene = new Scene(root);
            this.stage.setScene(scene);
            this.stage.setResizable(false);
            this.stage.setHeight(800);
            this.stage.setWidth(720);
            this.stage.show();
        });
    }
}
