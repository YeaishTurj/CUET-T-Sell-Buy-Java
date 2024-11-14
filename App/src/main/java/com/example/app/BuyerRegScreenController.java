package com.example.app;

import com.example.app.customDesign.AllItemsShowScreen;
import com.example.app.database.DatabaseConnection;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuyerRegScreenController {
    @FXML
    public Label success;
    @FXML
    public TextField name;
    @FXML
    public Label toast;
    @FXML
    public TextField email;
    @FXML
    public PasswordField password;
    @FXML
    public AnchorPane buyerAnchor;
    @FXML
    public void onRegistered(ActionEvent actionEvent) throws SQLException, InterruptedException, IOException {
        String userEmail=email.getText();
        String userName=name.getText();
        String userPass=password.getText();
        int perfect=0;
        if (userEmail.isEmpty()) {
            toast.setVisible(true);
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("Please enter your CUET Student mail address");
        }
        else if (userPass.isEmpty()) {
            toast.setVisible(true);
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("Please enter your password");
        }
        else if (checkMail(userEmail)) {
            //====== if mail is perfect ====//
            if(!password.getText().isEmpty()) perfect =1;
        }
        else if (checkUserExist(userEmail)) { // check here already registered or not
            toast.setVisible(true);
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("User Already Exists");
        }
        if (perfect == 1) {
            //====== store the data in database =======//
            storeDataInDB(userName,userEmail,userPass);
            //====== after complete =======//
            toast.setText("Successfully Registered!");
            toast.setStyle("-fx-text-fill: green;");
            //====== navigation from BuyerRegScreen to ItemShowScreen ======//
            FXMLLoader loader = new FXMLLoader(getClass().getResource("all_item_show_screen.fxml"));
            Parent root = loader.load();
            AllItemsShowScreen.passedData(userName, userEmail, userPass);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            toast.setText("Failed to Registered!");
            toast.setStyle("-fx-text-fill: red;");
        }
    }
    private void storeDataInDB(String userName, String userEmail, String userPass) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws SQLException {
                Connection con = null;
                PreparedStatement ps = null;
                try {
                    con = DatabaseConnection.connect();
                    if (con != null) {
                        ps = con.prepareStatement("INSERT INTO buyer (name, email, password) VALUES (?, ?, ?)");
                        ps.setString(1, userName);
                        ps.setString(2, userEmail);
                        ps.setString(3, userPass);
                        int output = ps.executeUpdate();
                        if (output == 1) {
                            Platform.runLater(() -> success.setVisible(true));
                        } else {
                            Platform.runLater(() -> toast.setVisible(true));
                        }
                    } else {
                        System.out.println("Failed to connect to the database.");
                        Platform.runLater(() -> toast.setVisible(true));
                    }
                } catch (Exception e) {
                    Platform.runLater(() -> toast.setVisible(true));
                } finally {
                    assert ps != null;
                    ps.close();
                    con.close();
                }
                return null;
            }
        };
        new Thread(task).start();
    }
    private boolean checkUserExist(String comp) {
        //======== check here user already exist or not ========//

        return false;
    }
    public void backToLogin(ActionEvent e) throws IOException {
        //====== back to log in or registration screen ========//
        String logInPageFileName="buyer_signin_screen.fxml";
        FXMLLoader loader = new FXMLLoader(getClass().getResource(logInPageFileName));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
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
