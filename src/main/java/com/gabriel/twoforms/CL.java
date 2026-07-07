package com.gabriel.twoforms;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CL implements Initializable {

    @FXML
    private Button btn_Deposit;
    @FXML
    private Button btn_Bal;
    @FXML
    private Button btn_withdraw;
    @FXML
    private Button btn_Exit;
    @FXML
    private Button btn_Back;

    // Only present when checkbalance.fxml is the active FXML; null otherwise.
    @FXML
    private Label balanceLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Guard: this controller is reused across multiple FXML files,
        // so only run this if balanceLabel actually got injected.
        if (balanceLabel != null) {
            Account activeUser = CustomerLoginController.currentLoggedInAccount;
            if (activeUser != null) {
                balanceLabel.setText("Your current balance is: " + activeUser.getBalance());
            } else {
                balanceLabel.setText("No account logged in.");
            }
        }
    }

    @FXML
    public void OnCheckBalance(ActionEvent actionEvent) {
        Account activeUser = CustomerLoginController.currentLoggedInAccount;
        if (activeUser != null) {
            balanceLabel.setText("Your current balance is: " + activeUser.getBalance());
        }
    }

    @FXML
    public void OnBackToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void withdrawClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("withdraw.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void balanceClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkbalance.fxml"));
        Parent root;
        try {
            root = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void exitClick(ActionEvent actionEvent) {
        Platform.exit();
    }

    @FXML
    public void backClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainpage-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setTitle("Welcome Page");
        stage.setScene(scene);
    }

    @FXML
    public void depositClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deposit-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}