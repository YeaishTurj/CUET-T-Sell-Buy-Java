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

    // Constants for resource paths and dimensions
    private static final String FONT_PATH = "/fonts/";                // Directory where font files are located
    private static final String CSS_PATH = "/css/styles.css";        // Path to the CSS stylesheet
    private static final String FXML_PATH = "welcome_screen.fxml";            // Path to the FXML layout file
    private static final double WIDTH = 1024;                        // Width of the application window
    private static final double HEIGHT = 768;                       // Height of the application window

    @Override
    public void start(Stage stage) throws IOException {
        // Load custom fonts to be used in the application
        loadFonts();

        // Load the FXML file and get the root node for the scene
        AnchorPane root = loadFXML();

        // Create a scene with the loaded root node
        Scene scene = createScene(root);

        // Set up the stage with the created scene and specified dimensions
        stage.setScene(scene);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.setResizable(false); // Optional: Prevent resizing of the window
        stage.show(); // Display the application window
    }

    /**
     * Loads custom fonts from the specified directory.
     * The fonts are loaded for use in the JavaFX application.
     */
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

    /**
     * Loads the FXML file and returns the root node of the layout.
     *
     * @return AnchorPane - the root node of the loaded FXML layout
     * @throws IOException if the FXML file cannot be loaded
     */
    private AnchorPane loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH)); // Create an FXMLLoader
        return fxmlLoader.load(); // Load the FXML and return the root node
    }

    /**
     * Creates a Scene object with the specified root node and applies the CSS stylesheet.
     *
     * @param root The root node of the scene, typically loaded from FXML
     * @return Scene - the created Scene object
     */
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
