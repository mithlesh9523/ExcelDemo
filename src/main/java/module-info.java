module com.example.exceldemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens com.example.exceldemo to javafx.fxml;
    exports com.example.exceldemo;
}