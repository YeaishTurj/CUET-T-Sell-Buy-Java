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

    private static final String FONT_PATH = "/fonts/";                // Directory where font files are located
    private static final String CSS_PATH = "/css/styles.css";        // Path to the CSS stylesheet

    private static final String FXML_PATH = "welcome_screen.fxml";            // Path to the FXML layout file

    private static final double WIDTH = 1024;                        // Width of the application window
    private static final double HEIGHT = 768;                       // Height of the application window

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
        // Array of font file names to be loaded
        String[] fontFiles = {
                "Limelight.ttf", "MontaguSlab.ttf", "Agdasima.ttf", "Satisfy-Regular.ttf"
        };

        // Iterate through each font file and load it
        for (String fontFile : fontFiles) {
            Font.loadFont(
                    Objects.requireNonNull(getClass().getResource(FONT_PATH + fontFile)).toExternalForm(),
                    64 // Size of the font
            );
        }
    }

    private AnchorPane loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH)); // Create an FXMLLoader
        return fxmlLoader.load(); // Load the FXML and return the root node
    }

    private Scene createScene(AnchorPane root) {
        // Create a new scene with the root node and its preferred dimensions
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());

        // Load and apply the CSS stylesheet for the scene
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH)).toExternalForm());
        return scene; // Return the created scene
    }

    public static void main(String[] args) {
        launch(); // Launch the JavaFX application
    }
}
