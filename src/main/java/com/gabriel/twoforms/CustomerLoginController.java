package com.gabriel.twoforms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class CustomerLoginController {

    @FXML
    private TextField tf_login_acc;
    @FXML
    private Label lbl_error;

    public static Account currentLoggedInAccount = null;

    @FXML
    public void OnLoginSubmit(ActionEvent actionEvent) throws IOException {
        String inputStr = tf_login_acc.getText();

        try {
            int enteredAccNum = Integer.parseInt(inputStr);
            boolean accountFound = false;

            for (Account acc : Bank.accounts) {
                if (acc.getAccountNumber() == enteredAccNum) {
                    currentLoggedInAccount = acc;
                    accountFound = true;
                    break;
                }
            }

            if (accountFound) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-view.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Node node = (Node) actionEvent.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                lbl_error.setText("❌ Account number not found!");
            }
        } catch (NumberFormatException e) {
            lbl_error.setText("❌ Please enter a valid number!");
        }
    }
}
