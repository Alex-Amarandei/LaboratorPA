package app.controllers;

import app.enums.Piece;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import static app.enums.Piece.BLACK;
import static app.enums.Piece.WHITE;
import static javafx.geometry.Pos.BOTTOM_CENTER;
import static javafx.geometry.Pos.TOP_CENTER;
import static javafx.scene.Cursor.OPEN_HAND;

public class GameController {
    private BufferedReader fromServer;
    private PrintWriter toServer;
    private Piece player;
    private int intendedFrom = -1;
    private int intendedTo = -1;
    private boolean sentMove = false;
    private boolean hasRolled = false;

    @FXML
    private AnchorPane backgroundPane;
    @FXML
    private Label gameRoomLabel;
    @FXML
    private Label whiteLabel;
    @FXML
    private Label blackLabel;
    @FXML
    private ImageView firstDie;
    @FXML
    private ImageView secondDie;
    @FXML
    private Label capturedNumberLabel;
    @FXML
    private Label outNumberLabel;
    @FXML
    private GridPane leftGridPane;
    @FXML
    private GridPane rightGridPane;
    @FXML
    private Button rollButton;

    public void startGame(BufferedReader fromServer, PrintWriter toServer, int roomNumber, String whiteUsername, String blackUsername, String playerType) {
        this.fromServer = fromServer;
        this.toServer = toServer;
        this.player = playerType.equals(WHITE.toString()) ? WHITE : BLACK;

        setUI(roomNumber, whiteUsername, blackUsername);
        makeSpotsTargetable();
        redraw();
    }

    private void keepListening() {
        redraw();
    }

    private void redraw() {
        /*
         * 0: turn
         * 1: board
         * 2: whiteCaptured
         * 3: blackCaptured
         * 4: whiteOut
         * 5: blackOut
         * 6: dice
         */

        try {
            sentMove = false;
            String[] boardData = fromServer.readLine().split(":");
            if (boardData[0].equalsIgnoreCase("invalid")) return;

            Platform.runLater(() -> {
                backgroundPane.setMouseTransparent(!player.toString().equals(boardData[0]));
                if(player.toString().equals(boardData[0])) rollButton.setDisable(hasRolled);
                else rollButton.setDisable(true);

                setPieces(boardData[1]);
                if (player.equals(WHITE)) {
                    capturedNumberLabel.setText(boardData[2]);
                    outNumberLabel.setText(boardData[4]);
                } else {
                    capturedNumberLabel.setText(boardData[3]);
                    outNumberLabel.setText(boardData[5]);
                }
                if (boardData[0].equalsIgnoreCase("White")) {
                    setDie(1, Integer.parseInt(boardData[6].split("&")[0]), "White");
                    setDie(2, Integer.parseInt(boardData[6].split("&")[1]), "White");
                } else {
                    setDie(1, Integer.parseInt(boardData[6].split("&")[0]), "Black");
                    setDie(2, Integer.parseInt(boardData[6].split("&")[1]), "Black");
                }
            });
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void setPieces(String boardData) {
        String[] board = boardData.split("&");

        for (int i = 0; i < 6; i++) {
            if (board[i].charAt(0) != 'x') {
                StackPane stackPane = (StackPane) rightGridPane.getChildren().get(5 - i);
                buildPieces(board[i], stackPane, TOP_CENTER);
            }
        }

        for (int i = 6; i < 12; i++) {
            if (board[i].charAt(0) != 'x') {
                StackPane stackPane = (StackPane) leftGridPane.getChildren().get(11 - i);
                buildPieces(board[i], stackPane, TOP_CENTER);
            }
        }

        for (int i = 12; i < 18; i++) {
            if (board[i].charAt(0) != 'x') {
                StackPane stackPane = (StackPane) leftGridPane.getChildren().get(i - 6);
                buildPieces(board[i], stackPane, BOTTOM_CENTER);
            }
        }

        for (int i = 18; i < 24; i++) {
            if (board[i].charAt(0) != 'x') {
                StackPane stackPane = (StackPane) rightGridPane.getChildren().get(i - 12);
                buildPieces(board[i], stackPane, BOTTOM_CENTER);
            }
        }
    }

    private void setUI(int roomNumber, String whiteUsername, String blackUsername) {
        gameRoomLabel.setText("Game Room #" + roomNumber);
        whiteLabel.setText(whiteUsername);
        blackLabel.setText(blackUsername);
    }

    private void setDie(int die, int number, String suffix) {
        File dieFile = new File("src/resources/" + number + suffix + ".png");
        try {
            ImageView dieImage = new ImageView(dieFile.toURI().toURL().toString());
            if (die == 1) firstDie.setImage(dieImage.getImage());
            else secondDie.setImage(dieImage.getImage());
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void buildPieces(String spot, StackPane stackPane, Pos alignment) {
        VBox vbox = customVBox(alignment);
        String paint = spot.charAt(0) == 'w' ? "#FFFDE4" : "#434343";
        int spotSize = Integer.parseInt(spot.substring(1));
        for (int j = 0; j < spotSize; j++) {
            Circle circle = customCircle(paint);
            vbox.getChildren().add(circle);
        }

        if (player.toString().toLowerCase().charAt(0) == spot.charAt(0)) {
            if (alignment.equals(TOP_CENTER)) {
                vbox.getChildren().get(vbox.getChildren().size() - 1).setCursor(OPEN_HAND);
                makeClickable((Circle) vbox.getChildren().get(vbox.getChildren().size() - 1));
            } else {
                vbox.getChildren().get(0).setCursor(OPEN_HAND);
                makeClickable((Circle) vbox.getChildren().get(0));
            }
        }

        if (stackPane.getChildren().size() > 1) stackPane.getChildren().remove(1);
        stackPane.getChildren().add(vbox);
    }

    private VBox customVBox(Pos alignment) {
        VBox vbox = new VBox();
        vbox.setSpacing(2);
        vbox.setAlignment(alignment);
        vbox.setPrefHeight(230);
        vbox.setPrefWidth(55.5);
        return vbox;
    }

    private Circle customCircle(String paint) {
        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setFill(Paint.valueOf(paint));
        circle.setStrokeWidth(0);
        return circle;
    }

    public void rollClicked(MouseEvent mouseEvent) {
        Thread sendRoll = new Thread(() -> {
            rollButton.setDisable(true);
            hasRolled = true;
            toServer.println("ROLL");
            Platform.runLater(this::redraw);
        });
        sendRoll.start();
    }

    private void makeClickable(Circle circle) {
        circle.setOnMouseClicked(event -> {
            setIntendedFrom(circle);
            System.out.println("From: " + intendedFrom);
        });
    }

    private void setIntendedFrom(Circle circle) {
        for (int i = 0; i < 12; i++) {
            StackPane stackPane1 = (StackPane) leftGridPane.getChildren().get(i);
            if (stackPane1.getChildren().size() > 1) {
                VBox vbox1 = (VBox) stackPane1.getChildren().get(1);
                if (vbox1.getChildren().size() > 1) {
                    if (vbox1.getChildren().contains(circle)) {
                        intendedFrom = leftGridPane.getChildren().indexOf(stackPane1);
                        if (intendedFrom < 6) intendedFrom = 11 - intendedFrom;
                        else intendedFrom = intendedFrom + 6;
                    }
                }
            }

            StackPane stackPane2 = (StackPane) rightGridPane.getChildren().get(i);
            if (stackPane2.getChildren().size() > 1) {
                VBox vbox2 = (VBox) stackPane2.getChildren().get(1);
                if (vbox2.getChildren().size() > 1) {
                    if (vbox2.getChildren().contains(circle)) {
                        intendedFrom = rightGridPane.getChildren().indexOf(stackPane2);
                        if (intendedFrom < 6) intendedFrom = 5 - intendedFrom;
                        else intendedFrom = intendedFrom + 12;
                    }
                }
            }
        }
    }

    private void makeSpotsTargetable() {
        leftGridPane.getChildren().forEach(spot -> {
            spot.setOnMouseClicked(event -> {
                intendedTo = leftGridPane.getChildren().indexOf(spot);
                if (intendedTo < 6) {
                    if (11 - intendedTo != intendedFrom) {
                        intendedTo = 11 - intendedTo;
                        System.out.println("To: " + intendedTo);
                    } else intendedTo = -1;
                } else {
                    if (intendedTo + 6 != intendedFrom) {
                        intendedTo += 6;
                        System.out.println("To: " + intendedTo);
                    } else intendedTo = -1;
                }

                if (intendedTo != -1 && intendedFrom != -1 && !sentMove) {
                    sentMove = true;
                    Thread t = new Thread(() -> {
                        toServer.println(intendedFrom + "&" + intendedTo);
                        intendedFrom = -1;
                        intendedTo = -1;
                        Platform.runLater(this::redraw);
                    });
                    t.start();
                }
            });
        });

        rightGridPane.getChildren().forEach(spot -> {
            spot.setOnMouseClicked(event -> {
                intendedTo = rightGridPane.getChildren().indexOf(spot);
                if (intendedTo < 6) {
                    if (5 - intendedTo != intendedFrom) {
                        intendedTo = 5 - intendedTo;
                        System.out.println("To: " + intendedTo);
                    } else intendedTo = -1;
                } else {
                    if (intendedTo + 12 != intendedFrom) {
                        intendedTo += 12;
                        System.out.println("To: " + intendedTo);
                    } else intendedTo = -1;
                }

                if (intendedTo != -1 && intendedFrom != -1 && !sentMove) {
                    sentMove = true;
                    Thread t = new Thread(() -> {
                        toServer.println(intendedFrom + "&" + intendedTo);
                        intendedFrom = -1;
                        intendedTo = -1;
                        Platform.runLater(this::redraw);
                    });
                    t.start();
                }
            });
        });
    }
}
