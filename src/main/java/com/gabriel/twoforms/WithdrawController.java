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

public class WithdrawController {
    @javafx.fxml.FXML
    private Button btnSubmit;
    @javafx.fxml.FXML
    private TextField depAmt; // Keeps the fx:id from your FXML matching perfectly

    @javafx.fxml.FXML
    public void OnWithdrawSubmit(ActionEvent actionEvent) throws IOException {
        Account activeUser = CustomerLoginController.currentLoggedInAccount;

        if (activeUser != null) {
            double amount = Double.parseDouble(depAmt.getText());
            int autoAccNum = activeUser.getAccountNumber();

            // Assuming Bank has a withdraw method structured like: Bank.withdraw(amount, accountNum)
            // If it is named differently (e.g., Bank.deposit(-amount, autoAccNum)), tweak this line!
            Bank.withdraw(amount, autoAccNum);

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