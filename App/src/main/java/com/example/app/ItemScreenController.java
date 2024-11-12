package com.example.app;

import com.example.app.customDesign.Item;
import com.example.app.customDesign.Owner;
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
    private Hyperlink facebookLink;
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
        imageList.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/product_1.png"))));
        imageList.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/product_2.jpg"))));
        imageList.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/assets/product_3.png"))));
        size=imageList.size();
        itemImage.setImage(imageList.get(index));
        productName.setText("So elegant T-Shirt");
        productPrice.setText("Price:2777 tk");
        productQuantity.setText("Quantity:11");
        productDescription.setText("1.1000 gsm \n 2.Color guaranty \n 3. blah \n4.blah");
        ownerName.setText("HashCode");
        wAppNumber.setText("01813635343");
        contactNo.setText("01815505922");
    }
    //======== item details get  from buyer_show_screen ==========/
    public void getDetails(Owner owner, Item item){
        //====== set images in the image list ======//

        //====== set product details ( name,price,quantity,detail ) =====//
        productDescription.setText(item.getDescription());
        productName.setText(item.getTitle());
        productPrice.setText(Integer.toString(item.getPrice()));
        productQuantity.setText(Integer.toString(item.getQuantity()));
        //====== set owner details ( name, w.app no, contact no, fb link ) ======//
        ownerName.setText(owner.getName());
        contactNo.setText(owner.getPhoneNumber());
        wAppNumber.setText(owner.getWAppNumber());
        facebookLink.setOnAction(event -> {
            try {Desktop.getDesktop().browse(new URI(owner.getFbLink()));}
            catch (Exception e) {System.out.println(e);}
        });
        emailId.setText(owner.getEmailId());
    }
    public void logOutUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login_screen.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void gotoItemShowScreen(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("all_item_show_screen.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void showLeftImage(MouseEvent mouseEvent) {
        index=Math.abs(index-1+size);
        index=(index%size);
        itemImage.setImage(imageList.get(index));
    }
    public void showRightImage(MouseEvent mouseEvent) {
        index=index+1;
        index=(index%size);
        itemImage.setImage(imageList.get(index));
    }
    public void navigateToLink(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.google.com"));
    }

    public void handleBackButtonClick(ActionEvent actionEvent) {
    }

    public void handleSignOut(ActionEvent actionEvent) {
    }
}
