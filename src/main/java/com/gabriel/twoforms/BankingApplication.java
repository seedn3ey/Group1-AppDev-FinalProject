package com.gabriel.twoforms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BankingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Bank.loadAccountsFromFile();
        FXMLLoader fxmlLoader = new FXMLLoader(BankingApplication.class.getResource("mainpage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 320);
        stage.setTitle("Welcome Page");
        stage.setScene(scene);
        stage.show();
    }
}
