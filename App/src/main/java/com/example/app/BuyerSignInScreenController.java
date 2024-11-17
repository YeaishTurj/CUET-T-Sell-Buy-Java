// completed

package com.example.app;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class BuyerSignInScreenController implements Initializable {

    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";
    private static final String BUYER_REG_SCREEN_FXML = "buyer_reg_screen.fxml";
    private static final String ALL_ITEM_SHOW_SCREEN = "all_item_show_screen.fxml";
    private static final String CSS_PATH = "/css/styles.css";
    private static final double SCREEN_WIDTH = 1024;
    private static final double SCREEN_HEIGHT = 768;

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button backButton, signInButton;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label singInFailed;

    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.requestFocus();
        Platform.runLater(() -> mainPane.requestFocus());
        connectToDatabase();
    }

    @FXML
    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/CUET_T_SELL_DB";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded!");
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
        }
    }

    @FXML
    private void handleBackButtonClick() throws IOException {
        Parent root = loadFXML(WELCOME_SCREEN_FXML);
        Stage stage = (Stage) backButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    public void handleSignInButtonClick() throws IOException {
        if (!isBuyerRegistered()) {
            singInFailed.setText("You are not a registered buyer!");
            return;
        }

        if (!isPasswordCorrect()) {
            singInFailed.setText("Incorrect Password!");
            return;
        }

        SessionData.setBuyerEmail(usernameField.getText());
        Parent root = loadFXML("buyer_page.fxml");
        Stage stage = (Stage) backButton.getScene().getWindow();
        setScene(stage, root);
    }

    private boolean isBuyerRegistered() {
        try {
            String query = "SELECT * FROM buyer WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, usernameField.getText());
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private boolean isPasswordCorrect() {
        try {
            String query = "SELECT * FROM buyer WHERE email = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, usernameField.getText());
            pstmt.setString(2, passwordField.getText());
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @FXML
    private void handleSignUpClick(MouseEvent event) {
        try {
            Parent root = loadFXML(BUYER_REG_SCREEN_FXML);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            setScene(stage, root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMouseEnter() {
        signInButton.setStyle("-fx-background-color: linear-gradient(to bottom, #4CAF50, #388E3C);"
                + "-fx-text-fill: #FFFFFF; -fx-font-family: 'Limelight';"
                + "-fx-font-weight: bold; -fx-font-size: 24;"
                + "-fx-background-radius: 15; -fx-padding: 10 30;"
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 8, 0, 2, 2);");
    }

    @FXML
    private void handleMouseExit() {
        signInButton.setStyle("-fx-background-color: linear-gradient(to bottom, #4CAF50, #2E7D32);"
                + "-fx-text-fill: #FFFFFF; -fx-font-family: 'Limelight';"
                + "-fx-font-weight: bold; -fx-font-size: 24;"
                + "-fx-background-radius: 15; -fx-padding: 10 30;"
                + "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 8, 0, 3, 3);");
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
}
