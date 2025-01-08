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

    private static final String FONT_PATH = "/fonts/";
    private static final String CSS_PATH = "/css/styles.css";

    private static final String FXML_PATH = "welcome_screen.fxml";

    private static final double WIDTH = 1024;
    private static final double HEIGHT = 768;

    @Override
    public void start(Stage stage) throws IOException {
        loadFonts();

        AnchorPane root = loadFXML();

        Scene scene = createScene(root);

        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(false); // Optional: Prevent resizing of the window
        stage.setTitle("CUET T-Sell & Buy : A T-shirt buying and selling platform for CUET"); // Set the title of the application window
        stage.show(); // Display the application window
    }

    private void loadFonts() {
        String[] fontFiles = {
                "Limelight.ttf", "MontaguSlab.ttf", "Agdasima.ttf", "Satisfy-Regular.ttf", "Roboto-Regular.ttf", "Roboto-Bold.ttf"
        };

        for (String fontFile : fontFiles) {
            Font.loadFont(
                    Objects.requireNonNull(getClass().getResource(FONT_PATH + fontFile)).toExternalForm(),
                    64
            );
        }
    }

    private AnchorPane loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH)); // Create an FXMLLoader
        return fxmlLoader.load(); // Load the FXML and return the root node
    }

    private Scene createScene(AnchorPane root) {
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH)).toExternalForm());
        return scene;
    }

    public static void main(String[] args) {
        launch();
    }
}
