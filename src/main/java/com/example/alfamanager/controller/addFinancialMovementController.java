package com.example.alfamanager.controller;

import com.example.alfamanager.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addFinancialMovementController {


    @FXML
    private TextField descriptionFinancialMovement;

    @FXML
    private TextField valueFinancialMovement;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private TextField dateFinancialMovement;

    private FinancialMovementList financialMovementList;

    public void setFinancialMovementList(FinancialMovementList financialMovementList) {
        this.financialMovementList = financialMovementList;
    }


    @FXML
    public void onAddFinancialMovement(ActionEvent event) {
        String description = descriptionFinancialMovement.getText();
        String value = valueFinancialMovement.getText();
        String type = typeChoiceBox.getValue();
        String dateString = dateFinancialMovement.getText();
        Date dateFormatDate = null;

        if (type != null) {

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                dateFormatDate = format.parse(dateString);


                if (type.equals("Income")) {

                    try {
                        double valueDouble = Double.parseDouble(value);
                        Income income = new Income(description, valueDouble, dateFormatDate);
                        income.setType("Income");

                        financialMovementList.addFinancialMovement(income);

                        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
                        confirmation.setTitle("Success");
                        confirmation.setContentText("Income added successfully");
                        confirmation.showAndWait();

                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error adding income");
                        alert.setContentText("Write a valid number");
                        alert.showAndWait();
                    }
                } else if (type.equals("Cost")) {
                    try {
                        double valueDouble = Double.parseDouble(value);
                        Cost cost = new Cost(description, valueDouble, dateFormatDate);
                        cost.setType("Cost");

                        financialMovementList.addFinancialMovement(cost);


                        Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
                        confirmation.setTitle("Success");
                        confirmation.setContentText("Cost added successfully");
                        confirmation.showAndWait();

                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error adding");
                        alert.setContentText("Write a valid number");
                        alert.showAndWait();
                    }
                }

            } catch (ParseException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error adding income");
                alert.setContentText("The date must be valid");
                alert.showAndWait();
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error adding income");
            alert.setContentText("Select type to add");
            alert.showAndWait();

        }


    }


    @FXML
    public void onClose(ActionEvent event) {
        Stage stage = (Stage) descriptionFinancialMovement.getScene().getWindow();
        stage.close();
    }
}
