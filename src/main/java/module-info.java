module com.example.chatboxdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chatboxdemo to javafx.fxml;
    exports com.example.chatboxdemo;
}