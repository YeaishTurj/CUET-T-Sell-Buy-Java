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
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        if (perfect == 1) {
            boolean ok=checkUserExist(userEmail);
            if(ok){
                toast.setVisible(true);
                toast.setStyle("-fx-text-fill: red;");
                toast.setText("User Already Exists");
            }
            else {
                //====== store the data in database =======//
                storeDataInDB(userName, userEmail, userPass);
                //====== navigation from BuyerRegScreen to ItemShowScreen ======//
                controlRegisterAndLogin(actionEvent);
            }
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
        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement pstmt = connection.prepareStatement("SELECT 1 FROM buyer WHERE LOWER(email) = LOWER(?) LIMIT 1")) {
            pstmt.setString(1, comp);
            return pstmt.executeQuery().next();
        } catch (SQLException e) {
            System.out.println("Error checking user existence: " + e.getMessage());
            return false;
        }
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
    private void controlRegisterAndLogin(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.NONE); // Create a custom dialog
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK); // Add OK button
        Text content = new Text("Successfully registered!");
        content.setFont(Font.font("System", FontWeight.BOLD, 14));
        content.setStyle("-fx-fill: green;"); // Green color
        VBox container = new VBox(content);
        container.setStyle("-fx-alignment: center; -fx-padding: 10;"); // Center alignment and padding
        alert.getDialogPane().setContent(container);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    performTask(actionEvent);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void  performTask(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("buyer_signin_screen.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
