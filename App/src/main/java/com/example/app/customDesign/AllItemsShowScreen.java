package com.example.app.customDesign;

import com.example.app.database.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class AllItemsShowScreen implements Initializable {
    public TextField searchBox;
    @FXML
    private Text sortTypeText;
    @FXML
    public ImageView sortType;
    @FXML
    private VBox holder;
    //--------- globally declared variable for further modification ------------//
    public List<Product> listOfProduct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listOfProduct=getAllProductFromServer();
        loadDataInListView(listOfProduct);
    }
    public List<Product> getAllProductFromServer() {
        List<Product> products = new ArrayList<>();
        Connection connection = DatabaseConnection.connect(); // Assuming this returns a valid DB connection
        String sql = "SELECT product_id, seller_email, product_title, quantity, price, description, " +
                "mainImg, addImg1, addImg2, addImg3, addImg4 FROM product";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String sellerEmail = resultSet.getString("seller_email");
                String productTitle = resultSet.getString("product_title");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String description = resultSet.getString("description");
                byte[] mainImg = resultSet.getBytes("mainImg");
                byte[] addImg1 = resultSet.getBytes("addImg1");
                byte[] addImg2 = resultSet.getBytes("addImg2");
                byte[] addImg3 = resultSet.getBytes("addImg3");
                byte[] addImg4 = resultSet.getBytes("addImg4");
                mainImg = (mainImg == null) ? new byte[0] : mainImg;
                addImg1 = (addImg1 == null) ? new byte[0] : addImg1;
                addImg2 = (addImg2 == null) ? new byte[0] : addImg2;
                addImg3 = (addImg3 == null) ? new byte[0] : addImg3;
                addImg4 = (addImg4 == null) ? new byte[0] : addImg4;
                Product product = new Product(productId, sellerEmail, productTitle, quantity, price, description, mainImg, addImg1, addImg2, addImg3, addImg4);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    private void loadDataInListView(List<Product> myProduct){
        holder.getChildren().clear();
        for (int i=0;i<myProduct.size();i+=2) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/custom_item.fxml"));
            try {
                //====== now set those data in the custom design =====//
                HBox hBox = fxmlLoader.load();
                CustomItemController customItemController = fxmlLoader.getController();
                //==== first item here ====//
                Owner owner1=getSellerInfo(myProduct.get(i).sellerEmail);
                customItemController.setData1(myProduct.get(i),owner1);
                if(i+1<myProduct.size()) {
                    //==== second item here ====//
                    Owner owner2=getSellerInfo(myProduct.get(i+1).sellerEmail);
                    customItemController.setData2(myProduct.get(i+1),owner2);
                }
                holder.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private Owner getSellerInfo(String email) {
        Connection connection = DatabaseConnection.connect();
        Owner owner = null;
        String sql = "SELECT name, contact, w_app, facebook_link, email FROM seller WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String contact = resultSet.getString("contact");
                String wAppNumber = resultSet.getString("w_app");
                String fbLink = resultSet.getString("facebook_link");
                String emailId = resultSet.getString("email");
                owner = new Owner(name, wAppNumber, contact, fbLink, emailId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return owner;
    }
    //--------- later if we need to use the buyer information then get data from previous screen ------//
    public static void passedData(String userName, String userEmail, String userPass) {}
    public void showDialog(MouseEvent events) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("All", "High Quantity", "Low to High Price", "High to Low Price");
        comboBox.setPromptText("Select an item");
        VBox dialogPaneContent = new VBox(10);
        dialogPaneContent.getChildren().addAll(comboBox);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Select Item");
        dialog.getDialogPane().setContent(comboBox);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                String selectedItem = comboBox.getValue();
                switch (selectedItem){
                    case "High Quantity":{
                        sortItemList(3);
                        break;
                    }
                    case "Low to High Price":{
                        sortItemList(4);
                        break;
                    }
                    case "High to Low Price":{
                        sortItemList(5);
                        break;
                    }
                    default:{
                        sortItemList(6);
                        break;
                    }
                }
                sortTypeText.setText(selectedItem);
            }
        });
    }
    private void sortItemList(int index){
        //---- 3:High Quantity , 4:low price , 5:high price , 6: all just shuffle -----//
        if(index==3){
            listOfProduct.sort(Comparator.comparingInt(Product::getQuantity).reversed());
            loadDataInListView(listOfProduct);
        } else if (index==4) {
            listOfProduct.sort(Comparator.comparingDouble(Product::getPrice));
            loadDataInListView(listOfProduct);
        }else if (index==5) {
            listOfProduct.sort(Comparator.comparingDouble(Product::getPrice).reversed());
            loadDataInListView(listOfProduct);
        }else if (index==6) {
            Collections.shuffle(listOfProduct);
            loadDataInListView(listOfProduct);
        }
    }
    public void startSearchItem(MouseEvent event) {
        String search=searchBox.getText();
        if(search!=null){
            List<Product> searchItems=searchItems(listOfProduct,search);
            loadDataInListView(searchItems);
        }
    }
    //========== here we filter the item based n user search, searching will be in the title and description ===============//
    public static List<Product> searchItems(List<Product> listOfProduct, String searchTerm) {
        String lowerCaseSearchTerm = searchTerm.toLowerCase();
        return listOfProduct.stream()
                .filter(item -> item.getProductTitle().toLowerCase().contains(lowerCaseSearchTerm) || item.getDescription().toLowerCase().contains(lowerCaseSearchTerm))
                .collect(Collectors.toList());
    }
    public void handleBackButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/buyer_page.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.setHeight(768);
        stage.setWidth(1024);
        stage.show();
    }
    public void handleSignOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/welcome_screen.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
        stage.setScene(scene);
        stage.setHeight(768);
        stage.setWidth(1024);
        stage.show();
    }
}


