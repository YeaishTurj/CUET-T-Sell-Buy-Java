package com.example.app.customDesign;

import com.example.app.ItemScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomItemController implements Initializable {
    @FXML
    public ImageView itemImage;
    @FXML
    private Button more;
    @FXML
    public Text itemTitle;
    @FXML
    private Text itemPrice;
    @FXML
    private Text itemQuantity;
    public Owner itemOwner;
    public Item singleItem;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        more.setOnMouseClicked(this::seeDetails);
    }
    //------- here we receive the data of item and owner to show in the custom design ------//
    public void setData(Item item,Owner owner){
            itemTitle.setText(item.getTitle());
            itemPrice.setText(Integer.toString(item.getPrice()));
            itemQuantity.setText(Integer.toString(item.getQuantity()));
            singleItem=item;
            itemOwner=owner;
    }
    public void seeDetails(MouseEvent event) {
        try {
            //----- load the fxml where we will navigate ----//
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/app/item_screen.fxml"));
            Parent root = loader.load();
            //----- get the controller of the fxml file ------//
            ItemScreenController itemScreenController = loader.getController();
            //------- here we pass those data of clicked item to the single item screen ------
            itemScreenController.getDetails(itemOwner,singleItem);
            //----- Switch to the new scene -------//
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("Item clicked: " + itemTitle.getText());
    }
}
