package com.example.alfamanager.controller;

import com.example.alfamanager.MainApplication;
import com.example.alfamanager.model.Cost;
import com.example.alfamanager.model.FinancialMovement;
import com.example.alfamanager.model.FinancialMovementList;
import com.example.alfamanager.model.Income;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;


public class MainController {
    @FXML
    private Button seeCostsButton;
    @FXML
    private Button seeIncomesButton;
    @FXML
    private Button seeCostsAndIncomesButton;
    @FXML
    private Button addButtonFinancialMovement;

    @FXML
    private Label balanceLabel;

    @FXML
    private TableView<FinancialMovement> tableView;

    @FXML
    private TableColumn<FinancialMovement, String> descriptionColumn;

    @FXML
    private TableColumn<FinancialMovement, Double> valueColumn;

    @FXML
    private TableColumn<FinancialMovement, String> dateColumn;

    @FXML
    private TableColumn<FinancialMovement, String> typeColumn;


    private FinancialMovementList financialMovementList;

    private FilteredList<FinancialMovement> filteredMovements;

    private SortedList<FinancialMovement> sortedMovements;


    private void updateBalance() {
        double totalIncome = financialMovementList.getFinancialMovements().stream()
                .filter(movement -> movement instanceof Income)
                .mapToDouble(FinancialMovement::getValue)
                .sum();

        double totalCost = financialMovementList.getFinancialMovements().stream()
                .filter(movement -> movement instanceof Cost)
                .mapToDouble(FinancialMovement::getValue)
                .sum();

        double balance = totalIncome - totalCost;
        balanceLabel.setText("Balance: " + balance);
    }

    public void initialize(FinancialMovementList financialMovementList) {
        this.financialMovementList = financialMovementList;


        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        //Lista que filtra
        filteredMovements = new FilteredList<>(financialMovementList.getFinancialMovements());
        //Lista que organiza
        sortedMovements = new SortedList<>(filteredMovements);
        sortedMovements.setComparator((movement1, movement2) -> movement2.getDateFormatDate().compareTo(movement1.getDateFormatDate()));
        //Timeline que llama el metodo updateBalance cada cierto tiempo, para mantenerlo actualizado
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateBalance();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        tableView.setItems(sortedMovements);


    }


    @FXML
    public void onAddFinancialMovement(ActionEvent event) {
        FXMLLoader loader = MainApplication.renderView("add-financial-movement-view.fxml");
        addFinancialMovementController addFinancialMovementController = loader.getController();
        addFinancialMovementController.setFinancialMovementList(this.financialMovementList);


    }

    @FXML
    public void onShowCostsOnly(ActionEvent event) {

        filteredMovements.setPredicate(movement -> movement instanceof Cost);


    }

    @FXML
    public void onShowIncomesOnly(ActionEvent event) {
        filteredMovements.setPredicate(movement -> movement instanceof Income);


    }

    @FXML
    public void onShowAllMovements(ActionEvent event) {
        filteredMovements.setPredicate(null);


    }
}
