package com.example.app;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class WelcomeScreenController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        // Bind the ImageView's width and height to the AnchorPane's width and height
        imageView.fitWidthProperty().bind(rootPane.widthProperty());
        imageView.fitHeightProperty().bind(rootPane.heightProperty());
    }
}
