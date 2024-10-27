
package com.example.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class BuyerSignInScreenController {

    @FXML
    private Button backButton;

    // Method to handle back button click
    @FXML
    private void handleBackButtonClick() throws IOException {
        // Load the welcome screen FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome_screen.fxml"));
        Parent root = loader.load();

        // Get the current stage from the backButton's scene
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Method to handle the Sign Up text click
    @FXML
    private void handleSignUpClick(MouseEvent event) {
        try {
            // Load the buyer registration screen FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("buyer_reg_screen.fxml"));
            Parent root = loader.load();

            // Get the current stage from the mouse event source
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 1024, 768)); // Use appropriate dimensions for the new scene
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }
}
