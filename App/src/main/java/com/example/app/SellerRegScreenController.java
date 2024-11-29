package com.example.app;

import com.example.app.database.DatabaseConnection;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SellerRegScreenController {
    @FXML
    private Button backBtn;
    @FXML
    private TextField contactNo;
    @FXML
    private Button createAc;
    @FXML
    private TextField emailAddress;
    @FXML
    private TextField facebookLink;
    @FXML
    private PasswordField password;
    @FXML
    private TextField startUpName;
    @FXML
    private Label toast;
    @FXML
    private TextField wAppNumber;
    @FXML
    public void createAccount(ActionEvent actionEvent) throws IOException {
        String email=emailAddress.getText();
        String pass=password.getText();
        String name=startUpName.getText();
        String phone=contactNo.getText();
        String wApp=wAppNumber.getText();
        String fbLink=facebookLink.getText();
        int perfect=0;
        if (email.isEmpty()) {
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
        else if (checkUserExist(email)) { // check here already registered or not
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("User Already Exists");
        }
        else if (checkMail(email)) {
            //====== if mail is perfect ====//
            if(!pass.isEmpty() && !name.isEmpty() && !phone.isEmpty() && !wApp.isEmpty() && !fbLink.isEmpty()) perfect =1;
            else {
                toast.setStyle("-fx-text-fill: red;");
                toast.setText("Try Again");
            }
        }
        else {
            toast.setStyle("-fx-text-fill: red;");
            toast.setText("Try Again");
        }
        if (perfect == 1) {
            //====== store the data in database =======//
            storeDataInDB(email,pass,name,phone,wApp,fbLink);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Success");
            alert.setHeaderText("Congratulation!");
            alert.setContentText("You Have Successfully Register as A Seller.");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setStyle(
                    "-fx-font-family: 'Arial'; " +
                            "-fx-font-size: 14px; " +
                            "-fx-font-weight: bold; " +
                            "-fx-text-fill: green;"
            );

            dialogPane.lookup(".title").setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
            dialogPane.lookup(".header").setStyle("-fx-text-fill: blue; -fx-font-size: 14px;");
            dialogPane.lookup(".content").setStyle("-fx-text-fill: green; -fx-font-size: 15px;");

            ButtonType closeButton = new ButtonType("Close");
            alert.getButtonTypes().setAll(closeButton);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == closeButton) {
                openNewScreen();
            }
        }
    }
    private void openNewScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("seller_signin_screen.fxml"));
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setTitle("New Screen");
            newStage.setScene(new Scene(root, 1024, 768));
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void backToLogin(ActionEvent mouseEvent) throws IOException {
        //====== back to log in or registration screen ========//
        String logInPageFileName="seller_signin_screen.fxml";
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
    private boolean checkUserExist(String email) {
        try {
            Connection connection=DatabaseConnection.connect();
            String query = "SELECT * FROM buyer WHERE email = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, email);
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        //======== check here user already exist or not ========//
    }
    private void storeDataInDB(String email, String pass, String name, String phone, String wApp, String fbLink){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws SQLException {
                Connection con = null;
                PreparedStatement ps = null;
                try {
                    con = DatabaseConnection.connect();
                    if (con != null) {
                        ps = con.prepareStatement("INSERT INTO seller (name, email,password,contact,w_app,facebook_link) VALUES (?,?,?,?,?,?)");
                        ps.setString(1,name );
                        ps.setString(2, email);
                        ps.setString(3, pass);
                        ps.setString(4,phone);
                        ps.setString(5, wApp);
                        ps.setString(6, fbLink);
                        int output = ps.executeUpdate();
                        if (output == 1) {
                            con.commit();
                            toast.setText("Successfully Registered");
                            toast.setStyle("-fx-text-fill: green;");
                        }
                    } else {
                        toast.setText("Failed to connect to the database.");
                        toast.setStyle("-fx-text-fill: red;");
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
}
