package com.example.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuyerRegScreenController {
    @FXML
    public Label toast;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    public void onRegistered(ActionEvent actionEvent) {
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
        else if (comp.length() != 27) {
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("Invalid email address");
        } else if (checkMail(comp)) {
            //====== if mail is perfect ====//
            if(!password.getText().isEmpty()) perfect =1;
        }
        else if (checkUserExist(comp)) { // check here already registered or not
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("User Already Exists");
        }
        if (perfect == 1) {
            //====== store the data in database =======//

            //====== after complete =======//
            toast.setText("Successfully Registered!");
            toast.setStyle("-fx-text-fill: green;");
            toast.setVisible(true);
        }
    }

    private boolean checkUserExist(String comp) {
        //======== check here user already exist or not ========//
        return false;
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
}
