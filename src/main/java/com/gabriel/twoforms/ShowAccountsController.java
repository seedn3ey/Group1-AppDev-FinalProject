package com.gabriel.twoforms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.scene.control.TextInputDialog;
import java.util.Optional;

public class ShowAccountsController {

    @javafx.fxml.FXML
    private TableView <Account> table;
    @javafx.fxml.FXML
    private TableColumn <Account, Double> balance;
    @javafx.fxml.FXML
    private TableColumn <Account, String>name;
    @javafx.fxml.FXML
    private TableColumn <Account, Integer> account;
    @javafx.fxml.FXML
    private Button backClick;
    @javafx.fxml.FXML
    private Button DeleteClick;
    @javafx.fxml.FXML
    private Button ModifyClick;


    @javafx.fxml.FXML
    public void initialize() throws IOException {
        ObservableList <Account> data = FXCollections.observableArrayList(Bank.accounts);
        account.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        name.setCellValueFactory(new PropertyValueFactory<>("owner"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        table.setItems(data);
    }

    @javafx.fxml.FXML
    public void OnDeleteClick(ActionEvent actionEvent) throws IOException {
        Account selectedAccount = table.getSelectionModel().getSelectedItem();

        if (selectedAccount == null) {
            System.out.println("Please select an account row from the table first!");
            return;
        }

        Bank.accounts.remove(selectedAccount);
        table.getItems().remove(selectedAccount);

        saveAllAccountsToFile();

        System.out.println("Account removed completely!");
    }

    @javafx.fxml.FXML
    public void OnModifyClick(ActionEvent actionEvent) throws IOException {
        Account selectedAccount = table.getSelectionModel().getSelectedItem();

        if (selectedAccount == null) {
            System.out.println("Please select an account row from the table first!");
            return;
        }
        int targetAccNum = selectedAccount.getAccountNumber();
        TextInputDialog nameDialog = new TextInputDialog(selectedAccount.getOwner());
        nameDialog.setTitle("Modify Account");
        nameDialog.setHeaderText("Modifying Account: " + targetAccNum);
        nameDialog.setContentText("Enter new Owner Name:");
        Optional<String> nameResult = nameDialog.showAndWait();
        TextInputDialog balanceDialog = new TextInputDialog(String.valueOf(selectedAccount.getBalance()));
        balanceDialog.setTitle("Modify Account");
        balanceDialog.setHeaderText("Modifying Account: " + targetAccNum);
        balanceDialog.setContentText("Enter new Balance:");
        Optional<String> balanceResult = balanceDialog.showAndWait();

        if (nameResult.isPresent() && balanceResult.isPresent()) {
            String newName = nameResult.get();
            double newBalance = Double.parseDouble(balanceResult.get());
            Bank.accounts.remove(selectedAccount);
            table.getItems().remove(selectedAccount);
            Account modifiedAccount = new Account(targetAccNum, newName);
            if (newBalance > 0) {
                modifiedAccount.deposit(newBalance);
            }
            Bank.accounts.add(modifiedAccount);
            table.getItems().add(modifiedAccount);
            saveAllAccountsToFile();
            table.refresh();
            System.out.println("Account successfully swapped and modified!");
        }
    }

    @javafx.fxml.FXML
    public void OnBackClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin-view.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Scene scene = new Scene(root);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void saveAllAccountsToFile() {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("accounts.txt", false))) {
            for (Account acc : Bank.accounts) {
                writer.write(acc.getOwner() + "," + acc.getAccountNumber() + "," + acc.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

}
