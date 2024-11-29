package com.example.app.customDesign;

import com.example.app.ItemScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class CustomItemController implements Initializable {
    @FXML
    private HBox itemBox1;
    @FXML
    private HBox itemBox2;
    @FXML
    private ImageView itemImage1;
    @FXML
    private ImageView itemImage2;
    @FXML
    private Text itemPrice1;
    @FXML
    private Text itemPrice2;
    @FXML
    private Text itemQuantity1;
    @FXML
    private Text itemQuantity2;
    @FXML
    private Text itemTitle1;
    @FXML
    private Text itemTitle2;
    @FXML
    private Button more1;
    @FXML
    private Button more2;
    public Owner itemOwner1;
    public Product singleItem1;
    public Owner itemOwner2;
    public Product singleItem2;

    List<Image> product2ImageList= new ArrayList<>() ;
    List<Image> product1ImageList= new ArrayList<>() ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    //------- here we receive the data of item and owner to show in the custom design ------//
    public void setData1(Product item,Owner owner){
        itemTitle1.setText(item.getProductTitle());
        itemPrice1.setText(Double.toString(item.getPrice()));
        itemQuantity1.setText(Integer.toString(item.getQuantity()));
        singleItem1=item;
        itemOwner1=owner;
        product1ImageList=item.getImageList();
        itemImage1.setImage(product1ImageList.getFirst());
        itemBox1.setVisible(true);
    }
    public void setData2(Product item,Owner owner){
        itemTitle2.setText(item.getProductTitle());
        itemPrice2.setText(Double.toString(item.getPrice()));
        itemQuantity2.setText(Integer.toString(item.getQuantity()));
        singleItem2=item;
        itemOwner2=owner;
        product2ImageList=item.getImageList();
        itemImage2.setImage(product2ImageList.getFirst());
        itemBox2.setVisible(true);
    }
    public void seeDetails2(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/item_screen.fxml"));
            Parent root = loader.load();
            ItemScreenController itemScreenController = loader.getController();
            itemScreenController.getDetails(itemOwner2, singleItem2,product2ImageList);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
            stage.setScene(scene);
            stage.setHeight(768);
            stage.setWidth(1024);
            stage.show();

        } catch (IOException e) {
            System.out.println("Error");
        }
    }
    public void seeDetails1(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/item_screen.fxml"));
            Parent root = loader.load();
            ItemScreenController itemScreenController = loader.getController();
            itemScreenController.getDetails(itemOwner1, singleItem1,product1ImageList);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
            stage.setScene(scene);
            stage.setHeight(768);
            stage.setWidth(1024);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
