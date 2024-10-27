package com.example.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.IOException;

public class SellerSignInScreenController {

    @FXML
    private Button backButton;

    @FXML
    private void handleBackButtonClick() throws IOException {
        // Load the previous FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome_screen.fxml"));
        Parent root = loader.load();

        // Get the current stage from the backButton's scene
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
