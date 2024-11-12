//package com.example.app;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.Objects;
//
//public class SellerPageController {
//
//    // Constants for FXML file paths, CSS path, and dimensions
//    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";           // Path to the welcome screen FXML file
//    private static final String PRODUCT_MANAGEMENT_FMXL = "product_management.fxml";     // Path to the seller registration screen FXML file
//    private static final String PRODUCT_UPLOAD_FXML = "product_upload.fxml";                 // Path to the seller page screen FXML file
//    private static final String CSS_PATH = "/css/styles.css";                          // Path to the CSS stylesheet
//    private static final double SCREEN_WIDTH = 1024;                                   // Width for new scenes
//    private static final double SCREEN_HEIGHT = 768;                                   // Height for new scenes
//
//    @FXML
//    private Button manageProductsButton;
//    @FXML
//    public void handleManageProducts() throws IOException {
//        Parent root = loadFXML(PRODUCT_MANAGEMENT_FMXL);
//        Stage stage = (Stage) manageProductsButton.getScene().getWindow();
//        setScene(stage, root);
//    }
//
//    @FXML
//    private Button uploadProductButton;
//    @FXML
//    public void handleUploadProduct() throws IOException {
//        Parent root = loadFXML(PRODUCT_UPLOAD_FXML);
//        Stage stage = (Stage) uploadProductButton.getScene().getWindow();
//        setScene(stage, root);
//    }
//
//
//    /**
//     * Loads an FXML file and returns the root node of the layout.
//     *
//     * @param fxmlPath The relative path to the FXML file
//     * @return Parent - the root node of the loaded FXML layout
//     * @throws IOException if the FXML file cannot be loaded
//     */
//    private Parent loadFXML(String fxmlPath) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
//        return loader.load();
//    }
//
//
//    /**
//     * Sets a new scene for the specified stage with the given root node, default dimensions, and applies the CSS stylesheet.
//     *
//     * @param stage The stage on which to set the new scene
//     * @param root  The root node of the new scene layout
//     */
//    private void setScene(Stage stage, Parent root) {
//        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
//        // Load and apply the CSS stylesheet for the scene
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH)).toExternalForm());
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    @FXML
//    private Button signOutButton;
//    @FXML
//    public void handleSignOut() throws IOException {
//        Parent root = loadFXML(WELCOME_SCREEN_FXML);
//        Stage stage = (Stage) signOutButton.getScene().getWindow();
//        setScene(stage, root);
//    }
//
//    public void handleEdit(ActionEvent actionEvent) {
//    }
//
//    public void handleSave(ActionEvent actionEvent) {
//    }
//}


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
import java.util.Objects;

public class SellerPageController {

    // Constants for FXML file paths, CSS path, and dimensions
    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";
    private static final String PRODUCT_MANAGEMENT_FMXL = "product_management.fxml";
    private static final String PRODUCT_UPLOAD_FXML = "product_upload.fxml";
    private static final String CSS_PATH = "/css/styles.css";
    private static final double SCREEN_WIDTH = 1024;
    private static final double SCREEN_HEIGHT = 768;
    

    // Manage Products Button
    @FXML
    private Button manageProductsButton;
    @FXML
    public void handleManageProducts() throws IOException {
        Parent root = loadFXML(PRODUCT_MANAGEMENT_FMXL);
        Stage stage = (Stage) manageProductsButton.getScene().getWindow();
        setScene(stage, root);
    }

    // Upload Product Button
    @FXML
    private Button uploadProductButton;
    @FXML
    public void handleUploadProduct() throws IOException {
        Parent root = loadFXML(PRODUCT_UPLOAD_FXML);
        Stage stage = (Stage) uploadProductButton.getScene().getWindow();
        setScene(stage, root);
    }

    // Sign Out Button
    @FXML
    private Button signOutButton;
    @FXML
    public void handleSignOut() throws IOException {
        Parent root = loadFXML(WELCOME_SCREEN_FXML);
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        setScene(stage, root);
    }



    // Loads an FXML file and returns the root node of the layout
    private Parent loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        return loader.load();
    }

    // Sets a new scene for the specified stage with given root node, dimensions, and CSS stylesheet
    private void setScene(Stage stage, Parent root) {
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Button editCompanyButton;
    @FXML
    private Button saveCompanyButton;
    @FXML
    private Text companyName;
    @FXML
    private TextField newCompanyName;
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
    }

    @FXML
    private Button editPhoneButton;
    @FXML
    private Button savePhoneButton;
    @FXML
    private Text phoneNum;
    @FXML
    private TextField newPhoneNum;
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
    }

    @FXML
    private Button editWAButton;
    @FXML
    private Button saveWAButton;
    @FXML
    private Text waNum;
    @FXML
    private TextField newWANum;
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
    }

    @FXML
    private Button editFBButton;
    @FXML
    private Button saveFBButton;
    @FXML
    private Text fbLink;
    @FXML
    private TextField newFBLink;
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
    }


}
