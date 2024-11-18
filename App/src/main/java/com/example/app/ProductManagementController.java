package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class ProductManagementController {

    // Constants for FXML file paths, CSS path, and dimensions
    private static final String PRODUCT_UPDATE_FXML = "product_update.fxml";            // Path to the seller page screen FXML file
    private static final String SELLER_PAGE_FXML = "seller_page.fxml";                  // Path to the seller page screen FXML file
    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";    // Path to the product management screen FXML file
    private static final String CSS_PATH = "/css/styles.css";                          // Path to the CSS stylesheet
    private static final double SCREEN_WIDTH = 1024;                                   // Width for new scenes
    private static final double SCREEN_HEIGHT = 768;                                   // Height for new scenes

    private Connection connection = null;
    private String sellerEmail=SessionData.getSellerEmail();
    private int productId;

    @FXML
    private Button backButton;
    @FXML
    private void handleBackButtonClick() throws IOException {
        // Load the welcome screen using the specified FXML path
        Parent root = loadFXML(SELLER_PAGE_FXML);

        // Get the current stage and set the new scene with the specified dimensions
        Stage stage = (Stage) backButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    public void initialize() throws SQLException {
        connectToDatabase();
        showSellerProducts();
    }


    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/CUET_T_SELL_DB";
        String user = "root";
        String password = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded!");
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
        }catch (SQLException e){
            System.out.println("Connection failed!");
        }
    }


    private void showSellerProducts() throws SQLException {
        String query="SELECT * FROM product WHERE seller_email = ?";

        try(PreparedStatement pstmt = connection.prepareStatement(query);) {
           pstmt.setString(1,sellerEmail);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                productId = rs.getInt("product_id");
                String product_title = rs.getString("product_title");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String description = rs.getString("description");

                System.out.println(product_title+"|"+quantity+"|"+price+"|"+description);
                System.out.println("======================================================");
            }
        }
    }

    @FXML
    private Button updateProductButton;
    @FXML
    public void handleUpdateProductButtonClick() throws IOException {

        SessionData.setProductId(productId);

        Parent root = loadFXML(PRODUCT_UPDATE_FXML);
        Stage stage = (Stage) updateProductButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    private Button deleteProductButton;


    /**
     * Loads an FXML file and returns the root node of the layout.
     *
     * @param fxmlPath The relative path to the FXML file
     * @return Parent - the root node of the loaded FXML layout
     * @throws IOException if the FXML file cannot be loaded
     */
    private Parent loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        return loader.load();
    }


    /**
     * Sets a new scene for the specified stage with the given root node, default dimensions, and applies the CSS stylesheet.
     *
     * @param stage The stage on which to set the new scene
     * @param root  The root node of the new scene layout
     */
    private void setScene(Stage stage, Parent root) {
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        // Load and apply the CSS stylesheet for the scene
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }


    public void handleDeleteProductButtonClick(MouseEvent mouseEvent) {

    }

    @FXML
    private Button signOutButton;
    @FXML
    public void handleSignOut() throws IOException {
        Parent root = loadFXML(WELCOME_SCREEN_FXML);
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        setScene(stage, root);
    }

}
