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
    public void initialize() throws IOException {
        ObservableList <Account> data = FXCollections.observableArrayList(Bank.accounts);
        account.setCellValueFactory(new PropertyValueFactory<>("accountNumber"));
        name.setCellValueFactory(new PropertyValueFactory<>("owner"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        table.setItems(data);
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
}
