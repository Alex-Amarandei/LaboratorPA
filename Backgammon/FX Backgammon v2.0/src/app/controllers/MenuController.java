package app.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.apache.commons.validator.routines.InetAddressValidator;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.WARNING;

public class MenuController implements Initializable {
    public static final int SERVER_PORT = 42180;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane backgroundPane;
    @FXML
    private ImageView wallpaper;
    @FXML
    private Label titleLabel;
    @FXML
    private Label portLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button singlePlayerButton;
    @FXML
    private Button multiPlayerButton;
    @FXML
    private HBox buttonBox;
    @FXML
    private TextField portField;
    @FXML
    private TextField usernameField;

    @FXML
    private void gameModeSelected(ActionEvent actionEvent) {
        String ipAddress = portField.getText();
        String username = usernameField.getText();
        String gameMode = ((Button) actionEvent.getSource()).getText();

        try {
            if (argumentsAreValid(ipAddress, username)) {
                Socket server = new Socket(ipAddress, SERVER_PORT);
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
                PrintWriter toServer = new PrintWriter(new OutputStreamWriter(server.getOutputStream()), true);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Loading.fxml"));
                root = fxmlLoader.load();
                LoadingController loadingController = fxmlLoader.getController();
                stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setHeight(400);
                stage.setWidth(400);
                stage.show();

                Thread establishConnection = new Thread(() -> {
                    try {
                        toServer.println(username + "&" + gameMode);
                        loadingController.searchGameRoom(fromServer, toServer, stage);
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                });
                establishConnection.start();
            } else {
                Alert alert = new Alert(WARNING);
                alert.setHeaderText("All fields are mandatory.");
                alert.setContentText("Please complete all fields before proceeding.\n*Note that the IP Address must be valid and the username cannot contain the `&` and `:` characters.");
                alert.showAndWait();
                alert.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setWallpaper();
        setTitle();
        setLabels();
        setFields();
        setButtons();
    }

    private void setWallpaper() {
        wallpaper.fitWidthProperty().bind(backgroundPane.widthProperty());
        wallpaper.fitHeightProperty().bind(backgroundPane.heightProperty());
    }

    private void setTitle() {
        DoubleProperty titleSize = new SimpleDoubleProperty(1);
        titleSize.bind(backgroundPane.widthProperty().divide(990));

        titleLabel.prefHeightProperty().bind(backgroundPane.heightProperty().divide(2.28));
        titleLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", titleSize.asString(), "em;"));
    }

    private void setLabels() {
        DoubleProperty labelSize = new SimpleDoubleProperty(1);
        labelSize.bind(backgroundPane.widthProperty().divide(2880));

        portLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", labelSize.asString(), "em;"));
        usernameLabel.styleProperty().bind(Bindings.concat("-fx-font-size: ", labelSize.asString(), "em;"));
    }

    private void setFields() {
        DoubleProperty fieldSize = new SimpleDoubleProperty(1);
        fieldSize.bind(backgroundPane.widthProperty().divide(2880));

        portField.prefWidthProperty().bind(backgroundPane.widthProperty().divide(2.4));
        portField.prefHeightProperty().bind(backgroundPane.heightProperty().divide(16));

        usernameField.prefWidthProperty().bind(backgroundPane.widthProperty().divide(2.4));
        usernameField.prefHeightProperty().bind(backgroundPane.heightProperty().divide(16));

        portField.styleProperty().bind(Bindings.concat("-fx-font-size: ", fieldSize.asString(), "em;"));
        usernameField.styleProperty().bind(Bindings.concat("-fx-font-size: ", fieldSize.asString(), "em;"));
    }

    private void setButtons() {
        DoubleProperty buttonSize = new SimpleDoubleProperty(1);
        buttonSize.bind(backgroundPane.widthProperty().divide(2880));

        buttonBox.spacingProperty().bind(backgroundPane.widthProperty().divide(4));

        singlePlayerButton.prefWidthProperty().bind(backgroundPane.widthProperty().divide(4));
        singlePlayerButton.prefHeightProperty().bind(backgroundPane.heightProperty().divide(16));
        singlePlayerButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", buttonSize.asString(), "em;"));

        multiPlayerButton.prefWidthProperty().bind(backgroundPane.widthProperty().divide(4));
        multiPlayerButton.prefHeightProperty().bind(backgroundPane.heightProperty().divide(16));
        multiPlayerButton.styleProperty().bind(Bindings.concat("-fx-font-size: ", buttonSize.asString(), "em;"));
    }

    private boolean argumentsAreValid(String ipAddress, String username) {
        return InetAddressValidator.getInstance().isValid(ipAddress)
                && username.indexOf('&') + username.indexOf(':') == -2
                && !ipAddress.isEmpty() && !username.isEmpty();
    }
}
