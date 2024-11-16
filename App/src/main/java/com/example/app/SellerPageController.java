package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class SellerPageController {

    // Constants for FXML file paths, CSS path, and dimensions
    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";
    private static final String PRODUCT_MANAGEMENT_FMXL = "product_management.fxml";
    private static final String PRODUCT_UPLOAD_FXML = "product_upload.fxml";
    private static final String CSS_PATH = "/css/styles.css";
    private static final double SCREEN_WIDTH = 1024;
    private static final double SCREEN_HEIGHT = 768;

    // Database connection and seller ID
    private Connection connection = null;
    private String sellerId = "2104022";

    // FXML UI components
    @FXML
    private Text sellerEmailField, companyName, phoneNum, waNum, fbLink;
    @FXML
    private TextField newCompanyName, newPhoneNum, newWANum, newFBLink;
    @FXML
    private Button manageProductsButton, uploadProductButton, signOutButton;
    @FXML
    private Button editCompanyButton, saveCompanyButton;
    @FXML
    private Button editPhoneButton, savePhoneButton;
    @FXML
    private Button editWAButton, saveWAButton;
    @FXML
    private Button editFBButton, saveFBButton;

    // Initialization
    @FXML
    public void initialize() throws SQLException {
        connectToDatabase();
        showSellerInfo();
    }

    // Database connection
    @FXML
    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/CUET_T_SELL_DB";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded!");
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
        }
    }

    // Load seller information
    @FXML
    private void showSellerInfo() throws SQLException {
        if (connection != null) {
            try {
                Statement stmt = connection.createStatement();
                String sellerEmail = "u" + sellerId + "@student.cuet.ac.bd";
                String query = "SELECT * FROM seller WHERE email = '" + sellerEmail + "';";

                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String name = rs.getString("name");
                    String contact = rs.getString("contact");
                    String wApp = rs.getString("w_app");
                    String f_bLink = rs.getString("facebook_link");

                    System.out.println("Name: " + name + " Contact: " + contact + " WhatsApp: " + wApp + " Facebook: " + f_bLink);
                    companyName.setText(name);
                    sellerEmailField.setText(sellerEmail);
                    phoneNum.setText(contact);
                    waNum.setText(wApp);
                    fbLink.setText(f_bLink);
                }
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Database connection not established. Seller info not found.");
        }
    }

    // Navigation Handlers
    @FXML
    public void handleManageProducts() throws IOException {
        Parent root = loadFXML(PRODUCT_MANAGEMENT_FMXL);
        Stage stage = (Stage) manageProductsButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    public void handleUploadProduct() throws IOException {
        Parent root = loadFXML(PRODUCT_UPLOAD_FXML);
        Stage stage = (Stage) uploadProductButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    public void handleSignOut() throws IOException {
        Parent root = loadFXML(WELCOME_SCREEN_FXML);
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        setScene(stage, root);
    }

    // Edit and Save Handlers for Company Name
    @FXML
    public void handleEditCompany(ActionEvent actionEvent) {
        editCompanyButton.setVisible(false);
        saveCompanyButton.setVisible(true);
        companyName.setVisible(false);
        newCompanyName.setVisible(true);
    }

    @FXML
    public void handleSaveCompany(ActionEvent actionEvent) {
        editCompanyButton.setVisible(true);
        saveCompanyButton.setVisible(false);
        companyName.setVisible(true);
        newCompanyName.setVisible(false);
        companyName.setText(newCompanyName.getText());
        editSellerInfo(newCompanyName, "name");
    }

    // Edit and Save Handlers for Phone Number
    @FXML
    public void handleEditPhone(ActionEvent actionEvent) {
        editPhoneButton.setVisible(false);
        savePhoneButton.setVisible(true);
        phoneNum.setVisible(false);
        newPhoneNum.setVisible(true);
    }

    @FXML
    public void handleSavePhone(ActionEvent actionEvent) {
        editPhoneButton.setVisible(true);
        savePhoneButton.setVisible(false);
        phoneNum.setVisible(true);
        newPhoneNum.setVisible(false);
        phoneNum.setText(newPhoneNum.getText());
        editSellerInfo(newPhoneNum, "contact");
    }

    // Edit and Save Handlers for WhatsApp Number
    @FXML
    public void handleEditWA(ActionEvent actionEvent) {
        editWAButton.setVisible(false);
        saveWAButton.setVisible(true);
        waNum.setVisible(false);
        newWANum.setVisible(true);
    }

    @FXML
    public void handleSaveWA(ActionEvent actionEvent) {
        editWAButton.setVisible(true);
        saveWAButton.setVisible(false);
        waNum.setVisible(true);
        newWANum.setVisible(false);
        waNum.setText(newWANum.getText());
        editSellerInfo(newWANum, "w_app");
    }

    // Edit and Save Handlers for Facebook Link
    @FXML
    public void handleEditFB(ActionEvent actionEvent) {
        editFBButton.setVisible(false);
        saveFBButton.setVisible(true);
        fbLink.setVisible(false);
        newFBLink.setVisible(true);
    }

    @FXML
    public void handleSaveFB(ActionEvent actionEvent) {
        editFBButton.setVisible(true);
        saveFBButton.setVisible(false);
        fbLink.setVisible(true);
        newFBLink.setVisible(false);
        fbLink.setText(newFBLink.getText());
        editSellerInfo(newFBLink, "facebook_link");
    }

    // Helper Methods
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

    @FXML
    private void editSellerInfo(TextField newInfo, String infoType) {
        if (connection != null) {
            try {
                Statement stmt = connection.createStatement();
                String sellerEmail = "u" + sellerId + "@student.cuet.ac.bd";
                String query = "UPDATE seller SET " + infoType + " = '" + newInfo.getText() + "' WHERE email = '" + sellerEmail + "';";
                stmt.executeUpdate(query);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Database connection not established. Seller info not found.");
        }
    }
}
