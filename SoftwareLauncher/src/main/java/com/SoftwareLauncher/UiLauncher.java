package com.SoftwareLauncher;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.robot.Robot;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class UiLauncher extends Application {

    private int selectedIndex = 0;
    private final int rowSize = 3;
    private final int totalTiles = 9;

    @Override
    public void start(Stage stage) {

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.load("http://localhost:8080/home.html");

        Scene scene = new Scene(webView);
        stage.setScene(scene);
        stage.setTitle("SoftwareLauncher");
        stage.setMaximized(true);
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        stage.show();


        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            switch (code) {
                case RIGHT:
                    selectedIndex = (selectedIndex + 1) % totalTiles;
                    break;
                case LEFT:
                    selectedIndex = (selectedIndex - 1 + totalTiles) % totalTiles;
                    break;
                case UP:
                    selectedIndex = (selectedIndex - rowSize + totalTiles) % totalTiles;
                    break;
                case DOWN:
                    selectedIndex = (selectedIndex + rowSize) % totalTiles;
                    break;
                case ENTER:
                   handleClick(selectedIndex);
                    return;
                default:
                    return;
            }
            // Update tile selected in frontend
            webEngine.executeScript("selectTile(" + selectedIndex + ");");
        });

        // when WebView is loaded, it move focus on the scene
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                Platform.runLater(() -> {

                    double centerX = stage.getX() + stage.getWidth() / 2;
                    double centerY = stage.getY() + stage.getHeight() / 2;

                    // Simulated click
                    Robot robot = new Robot();
                    robot.mouseMove(centerX, centerY);
                    robot.mouseClick(javafx.scene.input.MouseButton.PRIMARY);

                });
            }
        });

        // Close request
        stage.setOnCloseRequest((WindowEvent t) -> {
            MainServer.stop();
            Platform.exit();
            System.exit(0);
        });
    }

    private void handleClick(int index) {
        SoftwareLauncher softwareLauncher = new SoftwareLauncher(index);
        softwareLauncher.run();
    }

    public static void main(String[] args) {
        Thread serverThread = new Thread(MainServer::start);
        serverThread.setDaemon(true);
        serverThread.start();
        launch(args);
    }
}
