package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ProductUpdateController {

    // Constants for FXML file paths, CSS path, and dimensions
    private static final String PRODUCT_MANAGEMENT_FXML = "product_management.fxml";            // Path to the seller page screen FXML file
    private static final String CSS_PATH = "/css/styles.css";                          // Path to the CSS stylesheet
    private static final double SCREEN_WIDTH = 1024;                                   // Width for new scenes
    private static final double SCREEN_HEIGHT = 768;                                   // Height for new scenes

    @FXML
    private Button backButton;


    @FXML
    private void handleBackButtonClick() throws IOException {
        // Load the welcome screen using the specified FXML path
        Parent root = loadFXML(PRODUCT_MANAGEMENT_FXML);

        // Get the current stage and set the new scene with the specified dimensions
        Stage stage = (Stage) backButton.getScene().getWindow();
        setScene(stage, root);
    }


    @FXML
    private Button updateProductButton;
    @FXML
    public void handleUpdateProduct() throws IOException {
        Parent root = loadFXML(PRODUCT_MANAGEMENT_FXML);
        Stage stage = (Stage) updateProductButton.getScene().getWindow();
        setScene(stage, root);
    }


    /**
     * Loads an FXML file and returns the root node of the layout.
     *
     * @param fxmlPath The relative path to the FXML file
     * @return Parent - the root node of the loaded FXML layout
     * @throws IOException if the FXML file cannot be loaded
     */
    private Parent loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        return loader.load();
    }


    /**
     * Sets a new scene for the specified stage with the given root node, default dimensions, and applies the CSS stylesheet.
     *
     * @param stage The stage on which to set the new scene
     * @param root  The root node of the new scene layout
     */
    private void setScene(Stage stage, Parent root) {
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        // Load and apply the CSS stylesheet for the scene
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
