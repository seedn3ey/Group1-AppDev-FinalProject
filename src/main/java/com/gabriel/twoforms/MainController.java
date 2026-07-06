package com.gabriel.twoforms;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @javafx.fxml.FXML
    private Button btn_admin;

    @javafx.fxml.FXML
    private Button btn_cust;

    @javafx.fxml.FXML
    public void adminwind(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin-view.fxml"));
        Parent root = (Parent)fxmlLoader.load();
        Scene scene = new Scene(root);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setTitle("Transaction Form");
        stage.setScene(scene);
    }


    @javafx.fxml.FXML
    public void customwind(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("custom-verif.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setTitle("Customer Login");
        stage.setScene(scene);
        stage.show();
    }

}
