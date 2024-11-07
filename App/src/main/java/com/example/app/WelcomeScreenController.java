package com.example.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class WelcomeScreenController {

    @FXML
    public Button buyerButton;

    @FXML
    public Button sellerButton;

    @FXML
    private void handleBuyerButtonClick() throws IOException {
        loadFXML("buyer_signin_screen.fxml");
    }

    @FXML
    private void handleSellerButtonClick() throws IOException {
        loadFXML("seller_signin_screen.fxml"); // Replace with your other FXML file
    }

    private void loadFXML(String fxmlFile) throws IOException {
        // Load the new FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();

        // Get the current stage from any component
        Stage stage = (Stage) buyerButton.getScene().getWindow(); // assuming `buyerButton` is in the current scene
        stage.setScene(new Scene(root));
        stage.show();
    }
}
