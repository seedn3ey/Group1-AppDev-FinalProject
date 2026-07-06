package com.gabriel.twoforms;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.util.Map;

public class CreateAccountController {

    private int accountNumber;
    @javafx.fxml.FXML
    private Button createSubmit;

    @javafx.fxml.FXML
    private TextField tf_name;
    @javafx.fxml.FXML
    private TextField acc_num;

    public TextField getAcc_num() {
        return acc_num;
    }

    public void setAcc_num(TextField acc_num) {
        this.acc_num = acc_num;
    }

    @javafx.fxml.FXML
    public void OnCreateSubmit(ActionEvent actionEvent) throws IOException {
        String name = tf_name.getText();
        String accNumStr = acc_num.getText();

        // 1. Create it in active memory
        Bank.createAccount(Integer.parseInt(accNumStr), name);

        // 2. Save it permanently (Starts with 0.0 balance)
        saveAccountToFile(name, accNumStr, 0.0);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("transaction-view.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Scene scene = new Scene(root);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    // Add this helper method inside CreateAccountController
    private void saveAccountToFile(String name, String accNumStr, double balance) {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("accounts.txt", true))) {
            writer.write(name + "," + accNumStr + "," + balance);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("❌ Error saving account: " + e.getMessage());
        }
    }

    public void SaveAcctoFile(String name, String accNumStr, double initialBalance) {
        // "true" means append to the file instead of wiping it clean every time
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true))) {

            // Writes: Name,AccountNumber,Balance
            writer.write(name + "," + accNumStr + "," + initialBalance);
            writer.newLine();

            System.out.println("✅ Account successfully saved to accounts.txt!");
        } catch (IOException e) {
            System.out.println("❌ Error writing to file: " + e.getMessage());
        }
    }


    public void backClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("admin-view.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Scene scene = new Scene(root);
        Node node = (Node)actionEvent.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.setTitle("Welcome Page");
        stage.setScene(scene);
    }
}
