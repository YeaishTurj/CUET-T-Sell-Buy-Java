//package com.example.app;
//
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
//import javax.swing.text.html.StyleSheet;
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.*;
//import java.text.BreakIterator;
//import java.util.Objects;
//import java.util.ResourceBundle;
//
//public class ProductUpdateController implements Initializable {
//
//    // Constants for FXML file paths, CSS path, and dimensions
//    private static final String PRODUCT_MANAGEMENT_FXML = "product_management.fxml";            // Path to the seller page screen FXML file
//    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";                    // Path to the welcome screen FXML file
//    private static final String CSS_PATH = "/css/styles.css";                          // Path to the CSS stylesheet
//    private static final double SCREEN_WIDTH = 1024;                                   // Width for new scenes
//    private static final double SCREEN_HEIGHT = 768;
//
//
//    private Connection connection = null;
//    private String sellerEmail=SessionData.getSellerEmail();// Height for new scenes
//    private int productId = SessionData.getProductId() ;
//
//    @FXML
//    private AnchorPane mainPane;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        mainPane.requestFocus();
//
//        // Apply a delayed request to ensure focus is not on any text field
//        Platform.runLater(() -> mainPane.requestFocus());
//        connectToDatabase();
//        try {
//            showSellerProducts();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private void connectToDatabase() {
//        String url = "jdbc:mysql://localhost:3306/CUET_T_SELL_DB";
//        String user = "root";
//        String password = "";
//
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver not loaded!");
//        }
//
//        try {
//            connection = DriverManager.getConnection(url, user, password);
//        }catch (SQLException e){
//            System.out.println("Connection failed!");
//        }
//    }
//
//    @FXML
//    private TextField productTitleField, productQuantityField, productPriceField, productDescriptionField;
//
//    private void showSellerProducts() throws SQLException {
//        String query="SELECT * FROM product WHERE product_id = ?";
//
//        try(PreparedStatement pstmt = connection.prepareStatement(query);) {
//            pstmt.setInt(1,productId);
//            ResultSet rs = pstmt.executeQuery();
//            while(rs.next()){
//                String product_title = rs.getString("product_title");
//                int quantity = rs.getInt("quantity");
//                double price = rs.getDouble("price");
//                String description = rs.getString("description");
//
//                // Set the text fields with the retrieved product details
//                productTitleField.setText(product_title);
//                productQuantityField.setText(String.valueOf(quantity));
//                productPriceField.setText(String.valueOf(price));
//                productDescriptionField.setText(description);
//            }
//        }
//    }
//
//
//    @FXML
//    private Button backButton;
//
//
//    @FXML
//    private void handleBackButtonClick() throws IOException {
//        // Load the welcome screen using the specified FXML path
//        Parent root = loadFXML(PRODUCT_MANAGEMENT_FXML);
//
//        // Get the current stage and set the new scene with the specified dimensions
//        Stage stage = (Stage) backButton.getScene().getWindow();
//        setScene(stage, root);
//    }
//
//
//    @FXML
//    private Button updateButton;
//    @FXML
//    public void handleUpdateProduct() throws IOException {
//        Parent root = loadFXML(PRODUCT_MANAGEMENT_FXML);
//        Stage stage = (Stage) updateButton.getScene().getWindow();
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
//    public void handleSignOutButtonClick() throws IOException {
//        Parent root = loadFXML(WELCOME_SCREEN_FXML);
//        Stage stage = (Stage) signOutButton.getScene().getWindow();
//        setScene(stage, root);
//    }
//
//    @FXML private ImageView mainImageView;
//    @FXML private ImageView additionalImageView1;
//    @FXML private ImageView additionalImageView2;
//    @FXML private ImageView additionalImageView3;
//    @FXML private ImageView additionalImageView4;
//    private File selectedMainFile;
//    private File selectedAdditionalFile1;
//    private File selectedAdditionalFile2;
//    private File selectedAdditionalFile3;
//    private File selectedAdditionalFile4;
//
//    // Utility method for file selection
//    private File selectFile(ImageView imageView, String title) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
//        fileChooser.setTitle(title);
//        return fileChooser.showOpenDialog(imageView.getScene().getWindow());
//    }
//
//    // Method to display selected image in ImageView
//    private void displaySelectedImage(File file, ImageView imageView) {
//        Image image = new Image(file.toURI().toString());
//        imageView.setImage(image);
//    }
//
//    public void handleUpdateMainClick(MouseEvent mouseEvent) {
//        File file = selectFile(mainImageView, "Select Main Photo");
//        if (file != null) {
//            displaySelectedImage(file, mainImageView);
//            selectedMainFile = file;
//        }
//    }
//    @FXML
//    private VBox updateMain;
//    public void handleMouseExitedUpdateMain(MouseEvent mouseEvent) {
//        updateMain.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 25; -fx-border-style: dotted; -fx-background-radius: 25; -fx-background-color: C6E7FF;");
//    }
//
//    public void handleMouseEnteredUpdateMain(MouseEvent mouseEvent) {
//        updateMain.setStyle("-fx-border-width: 5; -fx-border-color: #FFFFFF; -fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #C6E7FF; -fx-cursor: hand;");
//    }
//
//    public void handleUpdateAdditional1Click(MouseEvent mouseEvent) {
//        File file = selectFile(additionalImageView1, "Select Additional Photo 1");
//        if (file != null) {
//            displaySelectedImage(file, additionalImageView1);
//            selectedAdditionalFile1 = file;
//        }
//    }
//
//    public void handleUpdateAdditional2Click(MouseEvent mouseEvent) {
//        File file = selectFile(additionalImageView2, "Select Additional Photo 2");
//        if (file != null) {
//            displaySelectedImage(file, additionalImageView2);
//            selectedAdditionalFile2 = file;
//        }
//    }
//    @FXML
//    private HBox updateAdditional1;
//    public void handleMouseEnteredUpdateAdditional1(MouseEvent mouseEvent) {
//        updateAdditional1.setStyle("-fx-border-width: 5; -fx-border-color: #FFFFFF; -fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #C6E7FF; -fx-cursor: hand;");
//    }
//    @FXML
//    private HBox updateAdditional2;
//    public void handleMouseEnteredUpdateAdditional2(MouseEvent mouseEvent) {
//
//        updateAdditional2.setStyle("-fx-border-width: 5; -fx-border-color: #FFFFFF; -fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #C6E7FF; -fx-cursor: hand;");
//    }
//    @FXML
//    private HBox updateAdditional3;
//    public void handleMouseEnteredUpdateAdditional3(MouseEvent mouseEvent) {
//        updateAdditional3.setStyle("-fx-border-width: 5; -fx-border-color: #FFFFFF; -fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #C6E7FF; -fx-cursor: hand;");
//    }
//    @FXML
//    private HBox updateAdditional4;
//    public void handleMouseEnteredUpdateAdditional4(MouseEvent mouseEvent) {
//        updateAdditional4.setStyle("-fx-border-width: 5; -fx-border-color: #FFFFFF; -fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #C6E7FF; -fx-cursor: hand;");
//    }
//
//    public void handleMouseExitedUpdateAdditional4(MouseEvent mouseEvent) {
//        updateAdditional4.setStyle("-fx-border-color: black; -fx-background-radius: 0 0 25 0; -fx-background-color: C6E7FF; -fx-border-style: dotted; -fx-border-radius: 0 0 25 0;");
//    }
//
//    public void handleMouseExitedUpdateAdditional3(MouseEvent mouseEvent) {
//        updateAdditional3.setStyle("-fx-border-color: black; -fx-background-radius: 0 0 0 25; -fx-background-color: C6E7FF; -fx-border-style: dotted; -fx-border-radius: 0 0 0 25;");
//    }
//
//    public void handleMouseExitedUpdateAdditional2(MouseEvent mouseEvent) {
//        updateAdditional2.setStyle("-fx-border-color: black; -fx-background-radius: 0 25 0 0; -fx-background-color: C6E7FF; -fx-border-style: dotted; -fx-border-radius: 0 25 0 0;");
//    }
//
//    public void handleMouseExitedUpdateAdditional1(MouseEvent mouseEvent) {
//        updateAdditional1.setStyle("-fx-border-color: black; -fx-background-radius: 25 0 0 0; -fx-background-color: C6E7FF; -fx-border-style: dotted; -fx-border-radius: 25 0 0 0;");
//    }
//
//
//    public void handleUpdateAdditional3Click(MouseEvent mouseEvent) {
//        File file = selectFile(additionalImageView3, "Select Additional Photo 3");
//        if (file != null) {
//            displaySelectedImage(file, additionalImageView3);
//            selectedAdditionalFile3 = file;
//        }
//    }
//
//    public void handleUpdateAdditional4Click(MouseEvent mouseEvent) {
//        File file = selectFile(additionalImageView4, "Select Additional Photo 4");
//        if (file != null) {
//            displaySelectedImage(file, additionalImageView4);
//            selectedAdditionalFile4 = file;
//        }
//    }
//}


package com.example.app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class ProductUpdateController implements Initializable {

    private static final String PRODUCT_MANAGEMENT_FXML = "product_management.fxml";            // Path to the seller page screen FXML file
    private static final String WELCOME_SCREEN_FXML = "welcome_screen.fxml";                    // Path to the welcome screen FXML file
    private static final String CSS_PATH = "/css/styles.css";                          // Path to the CSS stylesheet
    private static final double SCREEN_WIDTH = 1024;                                   // Width for new scenes
    private static final double SCREEN_HEIGHT = 768;

    private Connection connection = null;
    private String sellerEmail = SessionData.getSellerEmail(); // Seller's email
    private int productId = SessionData.getProductId(); // Product ID

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField productTitleField, productQuantityField, productPriceField;
    @FXML
    private TextArea productDescriptionField;

    @FXML
    private Button backButton, updateButton, signOutButton;

    @FXML
    private ImageView mainImageView, additionalImageView1, additionalImageView2, additionalImageView3, additionalImageView4;

    @FXML
    private VBox updateMain;

    @FXML
    private HBox updateAdditional1, updateAdditional2, updateAdditional3, updateAdditional4;

    private File selectedMainFile, selectedAdditionalFile1, selectedAdditionalFile2, selectedAdditionalFile3, selectedAdditionalFile4;

    private byte[] mainImg=null, addImg1=null, addImg2=null, addImg3=null, addImg4=null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane.requestFocus();
        Platform.runLater(() -> mainPane.requestFocus());
        connectToDatabase();
        showSellerProducts();
    }

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
            System.out.println("Connection failed!");
        }
    }

    private void showSellerProducts() {
        String query = "SELECT * FROM product WHERE product_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String product_title = rs.getString("product_title");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                mainImg = rs.getBytes("mainImg");
                addImg1 = rs.getBytes("addImg1");
                addImg2 = rs.getBytes("addImg2");
                addImg3 = rs.getBytes("addImg3");
                addImg4 = rs.getBytes("addImg4");

                if (mainImg != null) {
                    mainImageView.setImage(new Image(new ByteArrayInputStream(mainImg)));
                }
                if(addImg1!=null){
                    additionalImageView1.setImage(new Image(new ByteArrayInputStream(addImg1)));
                }
                if(addImg2!=null){
                    additionalImageView2.setImage(new Image(new ByteArrayInputStream(addImg2)));
                }
                if(addImg3!=null){
                    additionalImageView3.setImage(new Image(new ByteArrayInputStream(addImg3)));
                }
                if(addImg4!=null){
                    additionalImageView4.setImage(new Image(new ByteArrayInputStream(addImg4)));
                }

                productTitleField.setText(product_title);
                productQuantityField.setText(String.valueOf(quantity));
                productPriceField.setText(String.valueOf(price));
                productDescriptionField.setText(description);
            } else {
                System.out.println("No product found with ID: " + productId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackButtonClick() throws IOException {
        Parent root = loadFXML(PRODUCT_MANAGEMENT_FXML);
        Stage stage = (Stage) backButton.getScene().getWindow();
        setScene(stage, root);
    }

    @FXML
    public void handleUpdateProduct() throws IOException {


        if(updateProductDetails()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Product Updated");
            alert.setHeaderText(null);
            alert.setContentText("Product details have been successfully updated!");

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-weight: bold;");
            dialogPane.lookup(".content.label").setStyle("-fx-text-fill: #28a745; -fx-font-weight: bold;");

            alert.showAndWait();
        } else {
            return;
        }

        Parent root = loadFXML(PRODUCT_MANAGEMENT_FXML);
        Stage stage = (Stage) updateButton.getScene().getWindow();
        setScene(stage, root);
    }

    private void showErrorDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14px; -fx-font-weight: bold;");

        dialogPane.lookup(".content").setStyle("-fx-text-fill: #d9534f; -fx-font-weight: bold;"); // Red text for content

        alert.setGraphic(null); // Remove default graphic
        alert.showAndWait();
    }

    private boolean updateProductDetails() throws IOException {

        String query = "UPDATE product SET product_title = ?, quantity = ?, price = ?, description = ?, mainImg = ?, addImg1 = ?, addImg2 = ?, addImg3 = ?, addImg4 =? WHERE product_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            String productTitle = productTitleField.getText();
            int productQuantity = Integer.parseInt(productQuantityField.getText());
            double productPrice = Double.parseDouble(productPriceField.getText());
            String productDescription = productDescriptionField.getText();
            if(selectedMainFile!=null) mainImg = fileToByteArray(selectedMainFile);
            if(selectedAdditionalFile1!=null) addImg1 = fileToByteArray(selectedAdditionalFile1);
            if(selectedAdditionalFile2!=null) addImg2 = fileToByteArray(selectedAdditionalFile2);
            if(selectedAdditionalFile3!=null) addImg3 = fileToByteArray(selectedAdditionalFile3);
            if(selectedAdditionalFile4!=null) addImg4 = fileToByteArray(selectedAdditionalFile4);

            if(productTitleField.getText().isEmpty() || productQuantityField.getText().isEmpty() || productPriceField.getText().isEmpty() || productDescriptionField.getText().isEmpty()){
                showErrorDialog("Invalid Input", "Please fill in all the fields.");
                return false;
            }

            pstmt.setString(1, productTitle);
            pstmt.setInt(2, productQuantity);
            pstmt.setDouble(3, productPrice);
            pstmt.setString(4, productDescription);
            pstmt.setBytes(5, mainImg);
            pstmt.setBytes(6,addImg1);
            pstmt.setBytes(7,addImg2);
            pstmt.setBytes(8,addImg3);
            pstmt.setBytes(9,addImg4);
            pstmt.setInt(10, productId);
            pstmt.executeUpdate();
            return true;
        } catch (NumberFormatException e) {
            showErrorDialog("Invalid Input", "Please enter valid values for quantity and price.");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            showErrorDialog("Database Error", "An error occurred while updating the product details. Please try again.");
            return false;
        } catch (IOException e) {
            showErrorDialog("File Error", "An error occurred while reading the image file. Please try again.");
            return false;
        } catch (Exception e) {
            showErrorDialog("Error", "An error occurred while updating the product details. Please try again.");
            return false;
        }
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

    @FXML
    public void handleSignOutButtonClick() throws IOException {
        Parent root = loadFXML(WELCOME_SCREEN_FXML);
        Stage stage = (Stage) signOutButton.getScene().getWindow();
        setScene(stage, root);
    }

    private File selectFile(ImageView imageView, String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        fileChooser.setTitle(title);
        return fileChooser.showOpenDialog(imageView.getScene().getWindow());
    }

    private byte[] fileToByteArray(File file) throws IOException {
        if (file == null) return null;

        try (FileInputStream fis = new FileInputStream(file);
             ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            return bos.toByteArray();
        }
    }

    private void displaySelectedImage(File file, ImageView imageView) {
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    public void handleUpdateMainClick(MouseEvent mouseEvent) {
        File file = selectFile(mainImageView, "Select Main Photo");
        if (file != null) {
            displaySelectedImage(file, mainImageView);
            selectedMainFile = file;
        }
    }

    public void handleMouseEnteredUpdateMain(MouseEvent mouseEvent) {
        updateMain.setStyle("-fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #fffef5;  -fx-effect: dropshadow(gaussian, black, 50, 0, 0, 0); -fx-cursor: hand; ");
    }

    public void handleMouseExitedUpdateMain(MouseEvent mouseEvent) {
        updateMain.setStyle("-fx-border-radius: 25; -fx-background-radius: 25; -fx-background-color: C6E7FF; -fx-effect: dropshadow(gaussian, black, 25, 0, 0, 0);");
    }

    public void handleUpdateAdditional1Click(MouseEvent mouseEvent) {
        File file = selectFile(additionalImageView1, "Select Additional Photo 1");
        if (file != null) {
            displaySelectedImage(file, additionalImageView1);
            selectedAdditionalFile1 = file;
        }
    }

    public void handleUpdateAdditional2Click(MouseEvent mouseEvent) {
        File file = selectFile(additionalImageView2, "Select Additional Photo 2");
        if (file != null) {
            displaySelectedImage(file, additionalImageView2);
            selectedAdditionalFile2 = file;
        }
    }

    public void handleUpdateAdditional3Click(MouseEvent mouseEvent) {
        File file = selectFile(additionalImageView3, "Select Additional Photo 3");
        if (file != null) {
            displaySelectedImage(file, additionalImageView3);
            selectedAdditionalFile3 = file;
        }
    }

    public void handleUpdateAdditional4Click(MouseEvent mouseEvent) {
        File file = selectFile(additionalImageView4, "Select Additional Photo 4");
        if (file != null) {
            displaySelectedImage(file, additionalImageView4);
            selectedAdditionalFile4 = file;
        }
    }

    public void handleMouseEnteredUpdateAdditional1(MouseEvent mouseEvent) {
        updateAdditional1.setStyle("-fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #fffef5;  -fx-effect: dropshadow(gaussian, rgba(0,0,0,1), 20, 0, 0, 0); -fx-cursor: hand; ");
    }

    public void handleMouseExitedUpdateAdditional1(MouseEvent mouseEvent) {
        updateAdditional1.setStyle("-fx-background-radius: 25 0 0 0; -fx-background-color: C6E7FF; -fx-border-radius: 25 0 0 0; -fx-effect: dropshadow(gaussian, rgba(0,0,0,1), 20, 0, 0, 0);");
    }

    public void handleMouseEnteredUpdateAdditional2(MouseEvent mouseEvent) {
        updateAdditional2.setStyle("-fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #fffef5;  -fx-effect: dropshadow(gaussian, rgba(0,0,0,1), 20, 0, 0, 0); -fx-cursor: hand; ");
    }

    public void handleMouseEnteredUpdateAdditional3(MouseEvent mouseEvent) {
        updateAdditional3.setStyle("-fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #fffef5;  -fx-effect: dropshadow(gaussian, rgba(0,0,0,1), 20, 0, 0, 0); -fx-cursor: hand; ");
    }

    public void handleMouseEnteredUpdateAdditional4(MouseEvent mouseEvent) {
        updateAdditional4.setStyle("-fx-border-radius: 50; -fx-background-radius: 50; -fx-background-color: #fffef5;  -fx-effect: dropshadow(gaussian, rgba(0,0,0,1), 20, 0, 0, 0); -fx-cursor: hand; ");
    }

    public void handleMouseExitedUpdateAdditional4(MouseEvent mouseEvent) {
        updateAdditional4.setStyle("-fx-background-radius: 0 0 25 0; -fx-background-color: C6E7FF; -fx-border-radius: 0 0 25 0; -fx-effect: dropshadow(gaussian, rgba(0,0,0,1), 20, 0, 0, 0);");
    }

    public void handleMouseExitedUpdateAdditional2(MouseEvent mouseEvent) {
        updateAdditional2.setStyle("-fx-background-radius: 0 25 0 0; -fx-background-color: C6E7FF; -fx-border-radius: 0 25 0 0; -fx-effect: dropshadow(gaussian, rgba(0,0,0,1), 20, 0, 0, 0);");
    }

    public void handleMouseExitedUpdateAdditional3(MouseEvent mouseEvent) {
        updateAdditional3.setStyle("-fx-background-radius: 0 0 0 25; -fx-background-color: C6E7FF; -fx-border-radius: 0 0 0 25; -fx-effect: dropshadow(gaussian, rgba(0,0,0,1), 20, 0, 0, 0);");
    }


}
