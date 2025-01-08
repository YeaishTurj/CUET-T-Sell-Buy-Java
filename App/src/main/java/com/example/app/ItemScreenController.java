package com.example.app;

import com.example.app.customDesign.Item;
import com.example.app.customDesign.Owner;
import com.example.app.customDesign.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemScreenController {
    @FXML
    private Text wAppNumber;
    @FXML
    private ImageView backBtn;
    @FXML
    private Text contactNo;
    @FXML
    private Text emailId;
    @FXML
    private Text facebookLink;
    @FXML
    private ImageView itemImage;
    @FXML
    private Text ownerName;
    @FXML
    private Label productDescription;
    @FXML
    private Text productName;
    @FXML
    private Text productPrice;
    @FXML
    private Text productQuantity;
    @FXML
    private ImageView rightImage;
    private List<Image> imageList = new ArrayList<>();
    private int index=0;
    private int size=1;

    //======== testing ========//
    @FXML
    public void initialize() {
    }
    //======== item details get  from buyer_show_screen ==========/
    public void getDetails(Owner owner, Product item,List<Image> imageLists){
        //====== set images in the image list ======//
        this.imageList=imageLists;
        //====== set product details ( name,price,quantity,detail ) =====//
        productDescription.setText(item.getDescription());
        productName.setText(item.getProductTitle());
        productPrice.setText(Double.toString(item.getPrice()));
        productQuantity.setText(Integer.toString(item.getQuantity()));
        //====== set owner details ( name, w.app no, contact no, fb link ) ======//
        ownerName.setText(owner.getName());
        contactNo.setText(owner.getPhoneNumber());
        wAppNumber.setText(owner.getWAppNumber());
//        facebookLink.setOnAction(event -> {
//            try {Desktop.getDesktop().browse(new URI(owner.getFbLink()));}
//            catch (Exception e) {System.out.println(e);}
//        });
        facebookLink.setText(owner.getFbLink());
        emailId.setText(owner.getEmailId());
        itemImage.setImage(imageLists.getFirst());
        size=imageLists.size();
    }
    public void showLeftImage(MouseEvent mouseEvent) {
        index=Math.abs(index-1+size);
        index=(index%size);
        itemImage.setImage(imageList.get(index));
        System.out.println("Index:"+index);
    }
    public void showRightImage(MouseEvent mouseEvent) {
        index=index+1;
        index=(index%size);
        itemImage.setImage(imageList.get(index));
        System.out.println("Index:"+index);
    }
    public void navigateToLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.google.com"));
    }
    public void handleBackButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("all_item_show_screen.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());
        stage.setScene(scene);
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
