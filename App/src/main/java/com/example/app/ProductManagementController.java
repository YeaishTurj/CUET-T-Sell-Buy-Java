package com.example.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;

public class ProductManagementController {

    private static final String PRODUCT_UPDATE_FXML = "product_update.fxml";
    private static final String SELLER_PAGE_FXML = "seller_page.fxml";
    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";
    private static final String CSS_PATH = "/css/styles.css";
    private static final double SCREEN_WIDTH = 1024;
    private static final double SCREEN_HEIGHT = 768;

    private Connection connection = null;
    private String sellerEmail = SessionData.getSellerEmail();
    private ObservableList<Product> productList = FXCollections.observableArrayList();

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> serialNumberColumn, productIdColumn, quantityColumn;
    @FXML
    private TableColumn<Product, String> productTitleColumn;
    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private Button backButton, updateProductButton, deleteProductButton, signOutButton;

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

    private void connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/CUET_T_SELL_DB";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed!");
        }
    }

    private void showSellerProducts() throws SQLException {
        String query = "SELECT * FROM product WHERE seller_email = ?";
        int serialNumber = 1;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, sellerEmail);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productTitle = rs.getString("product_title");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                productList.add(new Product(serialNumber++, productId, productTitle, quantity, price));
            }
            productTable.setItems(productList);
        }
    }

    private void setupTableColumns() {
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productTitleColumn.setCellValueFactory(new PropertyValueFactory<>("productTitle"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        centerAlignColumn(serialNumberColumn);
        centerAlignColumn(productTitleColumn);
        centerAlignColumn(quantityColumn);
        centerAlignColumn(priceColumn);

        serialNumberColumn.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        productTitleColumn.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        quantityColumn.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        priceColumn.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
    }

    private <T> void centerAlignColumn(TableColumn<Product, T> column) {
        column.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
                setAlignment(Pos.CENTER);
            }
        });
    }

    @FXML
    private void handleBackButtonClick() throws IOException {
        Parent root = loadFXML(SELLER_PAGE_FXML);
        Stage stage = (Stage) backButton.getScene().getWindow();
        setScene(stage, root);
    }

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
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No product selected!");
            alert.setContentText("Please select a product to update.");

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-weight: bold;");

            alert.showAndWait();
        }
    }

    @FXML
    public void handleDeleteProductButtonClick(MouseEvent mouseEvent) {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            // Create a confirmation alert
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText("Are you sure you want to delete this product?");
            confirmationAlert.setContentText("Product Title: " + selectedProduct.getProductTitle());

            DialogPane dialogPane = confirmationAlert.getDialogPane();
            dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-weight: bold;");

            // Wait for user response
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User confirmed deletion
                String deleteQuery = "DELETE FROM product WHERE product_id = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(deleteQuery)) {
                    pstmt.setInt(1, selectedProduct.getProductId());
                    pstmt.executeUpdate();

                    // Remove product from list and reset serial numbers
                    productList.remove(selectedProduct);
                    resetSerialNumbers();
                    productTable.refresh();
                    System.out.println("Product deleted successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Failed to delete product.");
                }
            } else {
                // User canceled deletion
                System.out.println("Product deletion canceled.");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No product selected!");
            alert.setContentText("Please select a product to delete.");

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-weight: bold;");

            alert.showAndWait();
        }
    }

    private void resetSerialNumbers() {
        int serialNumber = 1;
        for (Product product : productList) {
            product.setSerialNumber(serialNumber++);
        }
    }

    @FXML
    public void handleSignOut() throws IOException {
        Parent root = loadFXML(WELCOME_SCREEN_FXML);
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        setScene(stage, root);
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
