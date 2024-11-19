package com.example.app;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class testController {

    @FXML
    private ImageView carouselImageView;

    private final List<Image> carouselImages = new ArrayList<>();
    private int currentImageIndex = 0;

    @FXML
    public void initialize() {
        // Load carousel images
        carouselImages.add(new Image(getClass().getResourceAsStream("/assets/one_t.png")));
        carouselImages.add(new Image(getClass().getResourceAsStream("/assets/two_t.png")));
        carouselImages.add(new Image(getClass().getResourceAsStream("/assets/three_t.png")));
        carouselImages.add(new Image(getClass().getResourceAsStream("/assets/four_t.png")));

        // Set initial image
        carouselImageView.setImage(carouselImages.get(0));

        // Start the carousel animation
        startCarousel();
    }

    private void startCarousel() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> updateCarouselImage()),
                new KeyFrame(Duration.seconds(3)) // Change image every 3 seconds
        );

        timeline.setCycleCount(Timeline.INDEFINITE); // Loop indefinitely
        timeline.play();
    }

    private void updateCarouselImage() {
        currentImageIndex = (currentImageIndex + 1) % carouselImages.size();
        carouselImageView.setImage(carouselImages.get(currentImageIndex));
    }

    public void handleBuyerButtonClick(ActionEvent actionEvent) {
    }

    public void handleSellerButtonClick(ActionEvent actionEvent) {

    }
}
