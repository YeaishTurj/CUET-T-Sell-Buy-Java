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

public class BuyerSignInScreenController {

    // Constants for FXML file paths, CSS path, and dimensions
    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";           // Path to the welcome screen FXML file
    private static final String BUYER_REG_SCREEN_FXML = "buyer_reg_screen.fxml";       // Path to the buyer registration screen FXML file
    private static final String ALL_ITEM_SHOW_SCREEN = "all_item_show_screen.fxml";    // Path to the all item show screen FXML file
    private static final String CSS_PATH = "/css/styles.css";                          // Path to the CSS stylesheet
    private static final double SCREEN_WIDTH = 1024;                                   // Width for new scenes
    private static final double SCREEN_HEIGHT = 768;                                   // Height for new scenes

    @FXML
    private Button backButton;

    /**
     * Handles the back button click event and loads the welcome screen.
     *
     * @throws IOException if the welcome screen FXML file cannot be loaded
     */
    @FXML
    private void handleBackButtonClick() throws IOException {
        // Load the welcome screen using the specified FXML path
        Parent root = loadFXML(WELCOME_SCREEN_FXML);

        // Get the current stage and set the new scene with the specified dimensions
        Stage stage = (Stage) backButton.getScene().getWindow();
        setScene(stage, root);
    }

    /**
     * Handles the sign-up text click event and loads the buyer registration screen.
     *
     * @param event The mouse event triggering the sign-up action
     */
    @FXML
    private void handleSignUpClick(MouseEvent event) {
        try {
            // Load the buyer registration screen using the specified FXML path
            Parent root = loadFXML(BUYER_REG_SCREEN_FXML);

            // Get the current stage and set the new scene with specified dimensions
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            setScene(stage, root);
        } catch (IOException e) {
            e.printStackTrace(); // Log the exception stack trace
        }
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

    @FXML
    private Button signInButton;

    // Handle mouse entered event
    @FXML
    private void handleMouseEnter() {
        signInButton.setStyle("-fx-background-color: linear-gradient(to bottom, #4CAF50, #388E3C);"
                + "-fx-text-fill: #FFFFFF; -fx-font-family: 'Limelight';"
                + "-fx-font-weight: bold; -fx-font-size: 24;"
                + "-fx-background-radius: 15; -fx-padding: 10 30;"
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 2, 2);");
    }

    // Handle mouse exited event
    @FXML
    private void handleMouseExit() {
        signInButton.setStyle("-fx-background-color: linear-gradient(to bottom, #4CAF50, #2E7D32);"
                + "-fx-text-fill: #FFFFFF; -fx-font-family: 'Limelight';"
                + "-fx-font-weight: bold; -fx-font-size: 24;"
                + "-fx-background-radius: 15; -fx-padding: 10 30;"
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0, 3, 3);");
    }

    @FXML
    public void handleSignInButtonClick(ActionEvent actionEvent) throws IOException {
        // Load the welcome screen using the specified FXML path
        Parent root = loadFXML(ALL_ITEM_SHOW_SCREEN);

        // Get the current stage and set the new scene with the specified dimensions
        Stage stage = (Stage) backButton.getScene().getWindow();
        setScene(stage, root);
    }
}
