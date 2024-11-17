package com.example.app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProductUploadController implements Initializable {

    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";
    private static final String SELLER_PAGE_FXML = "seller_page.fxml";
    private static final String CSS_PATH = "/css/styles.css";
    private static final double SCREEN_WIDTH = 1024;
    private static final double SCREEN_HEIGHT = 768;

    public HBox uploadAdditional4;
    public HBox uploadAdditional3;
    public HBox uploadAdditional2;
    public HBox uploadAdditional1;

    @FXML private AnchorPane mainPane;
    @FXML private Button backButton;
    @FXML private Button uploadButton;
    @FXML private Button signOutButton;
    @FXML private ImageView mainImageView;
    @FXML private ImageView additionalImageView1;
    @FXML private ImageView additionalImageView2;
    @FXML private ImageView additionalImageView3;
    @FXML private ImageView additionalImageView4;
    @FXML private VBox uploadMain;

    private File selectedMainFile;
    private File selectedAdditionalFile1;
    private File selectedAdditionalFile2;
    private File selectedAdditionalFile3;
    private File selectedAdditionalFile4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.requestFocus();
        Platform.runLater(() -> mainPane.requestFocus());
    }

    @FXML
    private void handleBackButtonClick() throws IOException {
        Parent root = loadFXML(SELLER_PAGE_FXML);
        Stage stage = (Stage) backButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    public void handleUploadProductButtonClick() throws IOException {
        Parent root = loadFXML(SELLER_PAGE_FXML);
        Stage stage = (Stage) uploadButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    public void handleSignOutButtonClick() throws IOException {
        Parent root = loadFXML(WELCOME_SCREEN_FXML);
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    public void handleMouseEnteredUploadMain() {
        uploadMain.setStyle("-fx-border-width: 2; -fx-border-color: #42A5F5; -fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #C6E7FF; -fx-cursor: hand;");
    }

    @FXML
    public void handleMouseEnteredUploadAdditional1() {
        uploadAdditional1.setStyle("-fx-border-width: 2; -fx-border-color: #42A5F5; -fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #C6E7FF; -fx-cursor: hand;");
    }

    @FXML
    public void handleMouseEnteredUploadAdditional2() {
        uploadAdditional2.setStyle("-fx-border-width: 2; -fx-border-color: #42A5F5; -fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #C6E7FF; -fx-cursor: hand;");
    }

    @FXML
    public void handleMouseEnteredUploadAdditional3() {
        uploadAdditional3.setStyle("-fx-border-width: 2; -fx-border-color: #42A5F5; -fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #C6E7FF; -fx-cursor: hand;");
    }

    @FXML
    public void handleMouseEnteredUploadAdditional4() {
        uploadAdditional4.setStyle("-fx-border-width: 2; -fx-border-color: #42A5F5; -fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #C6E7FF; -fx-cursor: hand;");
    }

    @FXML
    public void handleMouseExitedUploadMain() {
        uploadMain.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 25; -fx-border-style: dotted; -fx-background-radius: 25; -fx-background-color: C6E7FF;");
    }

    @FXML
    public void handleMouseExitedUploadAdditional1() {
        uploadAdditional1.setStyle("-fx-border-color: black; -fx-background-radius: 25 0 0 0; -fx-background-color: C6E7FF; -fx-border-style: dotted; -fx-border-radius: 25 0 0 0;");
    }

    @FXML
    public void handleMouseExitedUploadAdditional2() {
        uploadAdditional2.setStyle("-fx-border-color: black; -fx-background-radius: 0 25 0 0; -fx-background-color: C6E7FF; -fx-border-style: dotted; -fx-border-radius: 0 25 0 0;");
    }

    @FXML
    public void handleMouseExitedUploadAdditional3() {
        uploadAdditional3.setStyle("-fx-border-color: black; -fx-background-radius: 0 0 0 25; -fx-background-color: C6E7FF; -fx-border-radius: 0 0 0 25; -fx-border-style: dotted;");
    }

    @FXML
    public void handleMouseExitedUploadAdditional4() {
        uploadAdditional4.setStyle("-fx-border-style: dotted; -fx-background-radius: 0 0 25 0; -fx-background-color: C6E7FF; -fx-border-radius: 0 0 25 0;");
    }

    public void handleUploadMainClick() {
        File file = selectFile(mainImageView, "Select Main Photo");
        if (file != null) {
            displaySelectedImage(file, mainImageView);
            selectedMainFile = file;
        }
    }

    public void handleUploadAdditional1Click() {
        File file = selectFile(additionalImageView1, "Select Additional Photo 1");
        if (file != null) {
            displaySelectedImage(file, additionalImageView1);
            selectedAdditionalFile1 = file;
        }
    }

    public void handleUploadAdditional2Click() {
        File file = selectFile(additionalImageView2, "Select Additional Photo 2");
        if (file != null) {
            displaySelectedImage(file, additionalImageView2);
            selectedAdditionalFile2 = file;
        }
    }

    public void handleUploadAdditional3Click() {
        File file = selectFile(additionalImageView3, "Select Additional Photo 3");
        if (file != null) {
            displaySelectedImage(file, additionalImageView3);
            selectedAdditionalFile3 = file;
        }
    }

    public void handleUploadAdditional4Click() {
        File file = selectFile(additionalImageView4, "Select Additional Photo 4");
        if (file != null) {
            displaySelectedImage(file, additionalImageView4);
            selectedAdditionalFile4 = file;
        }
    }

    private File selectFile(ImageView imageView, String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File file = fileChooser.showOpenDialog(imageView.getScene().getWindow());
        if (file == null) {
            showAlert("No File Selected", "Please select an image file to upload.");
        }
        return file;
    }

    private void displaySelectedImage(File file, ImageView imageView) {
        try {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        } catch (Exception e) {
            showAlert("Error Loading Image", "Could not load the selected image: " + e.getMessage());
        }
    }

    public void saveMainImageToDatabase() {
        saveImageToDatabase(selectedMainFile, "No Main Image Selected");
    }

    public void saveAdditionalImage1ToDatabase() {
        saveImageToDatabase(selectedAdditionalFile1, "No Additional Image 1 Selected");
    }

    public void saveAdditionalImage2ToDatabase() {
        saveImageToDatabase(selectedAdditionalFile2, "No Additional Image 2 Selected");
    }

    public void saveAdditionalImage3ToDatabase() {
        saveImageToDatabase(selectedAdditionalFile3, "No Additional Image 3 Selected");
    }

    public void saveAdditionalImage4ToDatabase() {
        saveImageToDatabase(selectedAdditionalFile4, "No Additional Image 4 Selected");
    }

    private void saveImageToDatabase(File file, String alertMessage) {
        if (file == null) {
            showAlert("No Image Selected", alertMessage);
        }
        // Database functionality can be added here later
    }

    private Parent loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        return loader.load();
    }

    private void setScene(Stage stage, Parent root) {
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
