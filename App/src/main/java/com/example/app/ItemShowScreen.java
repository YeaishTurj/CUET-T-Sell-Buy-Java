package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.TouchEvent;

import javax.swing.text.html.ImageView;

public class ItemShowScreen {
    @FXML
    private ListView<?> listView;

    @FXML
    private Button logOut;

    @FXML
    private ImageView profile;

    @FXML
    private ImageView search;

    @FXML
    private TextField searchBox;

    @FXML
    private ImageView sortItem;

    public static void passedData(String userName, String userEmail, String userPass) {

    }
    public void logOutUser(ActionEvent actionEvent) {
    }

    public void searchItem(TouchEvent touchEvent) {
    }

    public void sortItem(TouchEvent touchEvent) {

    }
}
