package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class BuyerPageController {
    @FXML
    private Button signOutButton;

    @FXML
    void gotoAllItemScreen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/all_item_show_screen.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setHeight(768);
        stage.setWidth(1024);
        stage.show();
    }
    @FXML
    void signOutBuyer(ActionEvent event) throws IOException {
        Parent root = loadFXML();
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    private Parent loadFXML() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("welcome_screen.fxml"));
        return loader.load();
    }
}
