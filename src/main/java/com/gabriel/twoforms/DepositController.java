package com.gabriel.twoforms;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class DepositController {
    @javafx.fxml.FXML
    private Button btnSubmit;
    @javafx.fxml.FXML
    private TextField depAmt;

    @javafx.fxml.FXML
    public void OnDepositSubmit(ActionEvent actionEvent) throws IOException {
        Account activeUser = CustomerLoginController.currentLoggedInAccount;

        if (activeUser != null) {
            double amount = Double.parseDouble(depAmt.getText());
            int autoAccNum = activeUser.getAccountNumber();

            // Perform deposit using native logic
            Bank.deposit(amount, autoAccNum);

            // Update text file permanently
            saveAllAccountsToFile();
        }

        // Send back to customer-view.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
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
            System.out.println("❌ Error updating balances: " + e.getMessage());
        }
    }
}