package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellerRegScreenController {
    @FXML
    private TextField contactNo;

    @FXML
    private Button createAc;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private TextField startUpName;
    @FXML
    private Label toast;
    @FXML
    public void createAccount(ActionEvent actionEvent) {
        String comp=email.getText();
        int perfect=0;
        if (comp.isEmpty()) {
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("Please enter your CUET Student mail address");
        }
        else if (password.getText().isEmpty()) {
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("Please enter your password");
        }
        else if (startUpName.getText().isEmpty()) {
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("Please enter your StartUp Name");
        }
        else if (contactNo.getText().isEmpty() || contactNo.getText().length() !=11) {
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("Please enter your 11 digit Contact No");
        }
        else if (comp.length() != 27) {
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("Invalid email address");
        } else if (checkMail(comp)) {
            //====== if mail is perfect ====//
            if(!password.getText().isEmpty() && !startUpName.getText().isEmpty() && !contactNo.getText().isEmpty()) perfect =1;
            else {
                toast.setStyle("-fx-text-fill: red;");
                toast.setText("Try Again");
            }
        }
        else if (checkUserExist(comp)) { // check here already registered or not
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("User Already Exists");
        }
        else {
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("Try Again");
        }

        if (perfect == 1) {
            //====== store the data in database =======//



            //====== after complete =======//
            toast.setText("Successfully Registered!");
            toast.setStyle("-fx-text-fill: green;");
            toast.setVisible(true);
        }

    }
    public void backToLogin(MouseEvent mouseEvent) throws IOException {
        //====== back to log in or registration screen ========//
        String logInPageFileName="";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(logInPageFileName));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public boolean checkMail(String mail) {
        String regex = "^u\\d{7}@student\\.cuet\\.ac\\.bd$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }
    private boolean checkUserExist(String comp) {
        //======== check here user already exist or not ========//
        return false;
    }
}
