package com.example.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class ProductManagementController {

    // Constants for FXML file paths, CSS path, and dimensions
    private static final String PRODUCT_UPDATE_FXML = "product_update.fxml";
    private static final String SELLER_PAGE_FXML = "seller_page.fxml";
    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";
    private static final String CSS_PATH = "/css/styles.css";
    private static final double SCREEN_WIDTH = 1024;
    private static final double SCREEN_HEIGHT = 768;

    // Database connection and session data
    private Connection connection = null;
    private String sellerEmail = SessionData.getSellerEmail(); // Retrieve seller email from session
    private ObservableList<Product> productList = FXCollections.observableArrayList();

    // FXML elements
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> serialNumberColumn; // New column for serial numbers
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private TableColumn<Product, String> productTitleColumn;
    @FXML
    private TableColumn<Product, Integer> quantityColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private Button backButton;
    @FXML
    private Button updateProductButton;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button signOutButton;

    /**
     * Initialize the controller, connect to the database, set up table columns, and load seller products.
     */
    @FXML
    public void initialize() {
        connectToDatabase();
        setupTableColumns();
        try {
            showSellerProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Database operations

    /**
     * Connect to the database.
     */
    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/CUET_T_SELL_DB";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
        }
    }

    /**
     * Load all products for the seller and populate the TableView.
     */
    private void showSellerProducts() throws SQLException {
        String query = "SELECT * FROM product WHERE seller_email = ?";
        int serialNumber = 1; // Initialize serial number

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, sellerEmail);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productTitle = rs.getString("product_title");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                // Add product with serial number to the list
                productList.add(new Product(serialNumber++, productId, productTitle, quantity, price));
            }
            productTable.setItems(productList);
        }
    }

    // Table setup and data binding

    /**
     * Set up the table columns with appropriate mappings.
     */
    private void setupTableColumns() {
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber")); // Bind serial number
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productTitleColumn.setCellValueFactory(new PropertyValueFactory<>("productTitle"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    // Button click handlers

    /**
     * Navigate to the previous seller page.
     */
    @FXML
    private void handleBackButtonClick() throws IOException {
        Parent root = loadFXML(SELLER_PAGE_FXML);
        Stage stage = (Stage) backButton.getScene().getWindow();
        setScene(stage, root);
    }

    /**
     * Navigate to the product update page for the selected product.
     */
    @FXML
    public void handleUpdateProductButtonClick() throws IOException {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            SessionData.setProductId(selectedProduct.getProductId());
            Parent root = loadFXML(PRODUCT_UPDATE_FXML);
            Stage stage = (Stage) updateProductButton.getScene().getWindow();
            setScene(stage, root);
        } else {
            System.out.println("No product selected!");
        }
    }

    /**
     * Delete the selected product from the database.
     */
    @FXML
    public void handleDeleteProductButtonClick(MouseEvent mouseEvent) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            String deleteQuery = "DELETE FROM product WHERE product_id = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
                pstmt.setInt(1, selectedProduct.getProductId());
                pstmt.executeUpdate();
                productList.remove(selectedProduct);
                System.out.println("Product deleted successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Failed to delete product.");
            }
        } else {
            System.out.println("No product selected!");
        }
    }

    /**
     * Log out and return to the welcome screen.
     */
    @FXML
    public void handleSignOut() throws IOException {
        Parent root = loadFXML(WELCOME_SCREEN_FXML);
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        setScene(stage, root);
    }

    // Utility methods for FXML loading and scene management

    /**
     * Load an FXML file and return the root node.
     */
    private Parent loadFXML(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        return loader.load();
    }

    /**
     * Set a new scene on the provided stage.
     */
    private void setScene(Stage stage, Parent root) {
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource(CSS_PATH)).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
