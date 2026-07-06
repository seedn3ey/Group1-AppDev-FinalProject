package com.gabriel.twoforms;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class CL {
    @javafx.fxml.FXML
    private Button btnSubmit;
    @javafx.fxml.FXML
    private Button btnExit;

    @javafx.fxml.FXML
    public void OnCheckBalance(ActionEvent actionEvent) {
        Account activeUser = CustomerLoginController.currentLoggedInAccount;

        if (activeUser != null) {
            // Show a quick JavaFX popup alert displaying their balance cleanly!
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account Balance");
            alert.setHeaderText("Hello, " + activeUser.getOwner() + "!");
            alert.setContentText("Your current balance is: $" + activeUser.getBalance());
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void OnBackToMenu(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("customer-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}