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

        Font.loadFont(Objects.requireNonNull(HelloApplication.class.getResource("/fonts/Limelight-Regular.ttf")).toExternalForm(), 64);
        Font.loadFont(Objects.requireNonNull(HelloApplication.class.getResource("/fonts/MontaguSlab.ttf")).toExternalForm(), 64);
        Font.loadFont(Objects.requireNonNull(HelloApplication.class.getResource("/fonts/Agdasima-Regular.ttf")).toExternalForm(), 64);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcome_screen.fxml"));
        AnchorPane root = fxmlLoader.load();
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}