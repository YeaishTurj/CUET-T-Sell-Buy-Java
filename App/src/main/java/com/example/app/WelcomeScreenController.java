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
        loadFXML("seller_signin_screen.fxml");
    }

    private void loadFXML(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();

        Stage stage = (Stage) buyerButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
