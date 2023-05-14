module com.example.employeemanager {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.alfamanager to javafx.fxml;
    exports com.example.alfamanager;

    opens com.example.alfamanager.controller to javafx.fxml;
    exports com.example.alfamanager.controller;

    opens com.example.alfamanager.model to javafx.fxml;
    exports com.example.alfamanager.model;
}