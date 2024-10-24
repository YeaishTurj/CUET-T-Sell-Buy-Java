package com.example.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class SwitchScreen {
    public SwitchScreen(AnchorPane currentAnchorPane,String fxml) throws IOException {
        AnchorPane nextAnchorPane= FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource(fxml)));
        currentAnchorPane.getChildren().retainAll();
        currentAnchorPane.getChildren().setAll(nextAnchorPane);
    }
}
