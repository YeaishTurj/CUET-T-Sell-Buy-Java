package com.example.app.customDesign;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    public List<Item> listOfItem;
    public List<Owner> listOfOwner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //-------- when the screen is lode then we need to fetch the data first ----------//
        //-------- data need to fetch to each item:
        //-------- we will store it in the in a global list , which will be later modified by dialog option
        /*
           *Owner: Name , wApp , phone , email , fbLink
           *Item: Title , price , quantity , description , imageLink
        */
        //------- call the function to load data ----------//
        listOfItem=itemList();
        loadDataInListView(listOfItem);
    }
    private List<Item> itemList(){
        List<Item> ls=new ArrayList<>();
        Item item1 = new Item();
        item1.setItem("Title of T-shirt1", 1200, 3, "","description-1");
        ls.add(item1);
        Item item2 = new Item();
        item2.setItem("Title of T-shirt2", 500, 1, "","description-1");
        ls.add(item2);
        Item item3 = new Item();
        item3.setItem("Title of T-shirt3", 400, 3, "","description-1");
        ls.add(item3);
        Item item4 = new Item();
        item4.setItem("Title of T-shirt4", 600, 6, "","description-1");
        ls.add(item4);
        return ls;
    }
    //--------- later if we need to use the buyer information then get data from previous screen ------//
    public static void passedData(String userName, String userEmail, String userPass) {}
    @FXML
    void logOutUser(ActionEvent event) {}
    @FXML
    void searchItem(TouchEvent event) {

    }
    public void showDialog(MouseEvent events) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("All", "New Arrival", "Old Classic", "High Quantity", "Low to High Price", "High to Low Price");
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
                    case "New Arrival":{
                        sortItemList(1);
                        break;
                    }
                    case "Old Classic":{
                        sortItemList(2);
                        break;
                    }
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
            System.out.println("under 3");
            listOfItem.sort(Comparator.comparingInt(Item::getQuantity).reversed());
            loadDataInListView(listOfItem);
        } else if (index==4) {
            System.out.println("under 4");
            listOfItem.sort(Comparator.comparingInt(Item::getPrice));
            loadDataInListView(listOfItem);
        }else if (index==5) {
            System.out.println("under 5");
            listOfItem.sort(Comparator.comparingInt(Item::getPrice).reversed());
            loadDataInListView(listOfItem);
        }else if (index==6) {
            System.out.println("under 6");
            Collections.shuffle(listOfItem);
            loadDataInListView(listOfItem);
        }
    }
    private void loadDataInListView(List<Item> myItem){
        holder.getChildren().clear();
        Owner owner=new Owner();
        owner.setOwner("Zia","01813635343","01813635343","www.facebook.com/zia002","u2104025@student.cuet.ac.bd");
        for (int i=0;i<myItem.size();i+=2) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/example/app/custom_item.fxml"));
            try {
                HBox hBox = fxmlLoader.load();
                CustomItemController customItemController = fxmlLoader.getController();
                customItemController.setData1(myItem.get(i),owner);
                if(i+1<myItem.size()) {
                    customItemController.setData2(myItem.get(i+1),owner);
                }
                holder.getChildren().add(hBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void startSearchItem(MouseEvent event) {
        String search=searchBox.getText();
        if(search!=null){
            List<Item> searchItems=searchItems(listOfItem,search);
            loadDataInListView(searchItems);
        }
    }
    public static List<Item> searchItems(List<Item> listOfItems, String searchTerm) {
        String lowerCaseSearchTerm = searchTerm.toLowerCase();
        return listOfItems.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(lowerCaseSearchTerm))
                .collect(Collectors.toList());
    }
}
