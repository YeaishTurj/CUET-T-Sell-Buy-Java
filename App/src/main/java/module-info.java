module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.app to javafx.fxml;
    exports com.example.app;
    exports com.example.app.customDesign;
    opens com.example.app.customDesign to javafx.fxml;
}