module com.example.csci217 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.media;
    
    opens com.example.csci217 to javafx.fxml;
    exports com.example.csci217;
}