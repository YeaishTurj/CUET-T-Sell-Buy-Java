
package com.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Load custom fonts
        Font.loadFont(Objects.requireNonNull(HelloApplication.class.getResource("/fonts/Limelight.ttf")).toExternalForm(), 64);
        Font.loadFont(Objects.requireNonNull(HelloApplication.class.getResource("/fonts/MontaguSlab.ttf")).toExternalForm(), 64);
        Font.loadFont(Objects.requireNonNull(HelloApplication.class.getResource("/fonts/Agdasima.ttf")).toExternalForm(), 64);
        Font.loadFont(Objects.requireNonNull(HelloApplication.class.getResource("/fonts/Satisfy-Regular.ttf")).toExternalForm(), 64);

        // Load FXML
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcome_screen.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());

        // Set the scene and dimensions for the stage
        stage.setScene(scene);
        stage.setWidth(1024); // Set width to 1024
        stage.setHeight(768); // Set height to 768
//        stage.setResizable(false); // Optional: Prevent resizing

        // Load CSS from the 'css' folder
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
