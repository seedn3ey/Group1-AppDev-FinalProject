package com.gabriel.twoforms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class Bank {
    static ArrayList<Account> accounts = new ArrayList<>();

    static void createAccount(int accountNumber, String owner) {

        Alert AccExist = new Alert(AlertType.INFORMATION);
        AccExist.setHeaderText("Account already exists!");
        AccExist.setTitle("Warning!");

        Alert NewAcc = new Alert(AlertType.INFORMATION);
        NewAcc.setHeaderText("Account created successfully");
        NewAcc.setTitle("Congrats!");

        for(Account acc:accounts) {
            if (acc.getAccountNumber() ==accountNumber) {
                System.out.println("Account already exists!");
                AccExist.showAndWait();
                return;
            }
        }

        Account account=new Account(accountNumber,owner);
        accounts.add(account);
        System.out.println("Account created successfully.");
        NewAcc.showAndWait();

    }

    static void deposit(double amount,int accountNumber) {
        Alert NoAcc = new Alert(AlertType.INFORMATION);
        NoAcc.setHeaderText("Account not found!");
        NoAcc.setTitle("Oh No!");
        for(Account acc:accounts) {
            if (acc.getAccountNumber() != accountNumber) {
                System.out.println("Account not found!");
                NoAcc.showAndWait();
            }
            else {
                 acc.deposit(amount);
                 break;
            }
        }
    }

    static void withdraw(double amount,int accountNumber) {
        Alert NoAcc = new Alert(AlertType.INFORMATION);
        NoAcc.setHeaderText("Account not found!");
        NoAcc.setTitle("Oh No!");

        for(Account acc:accounts) {
            if (acc.getAccountNumber() != accountNumber) {
                System.out.println("Account not found!");
                NoAcc.showAndWait();
            }
            else {
                acc.withdraw(amount);
                break;
            }
        }
    }

    static void Check(double amount,int accountNumber) {
        Alert Balance = new Alert(AlertType.INFORMATION);
        Balance.setHeaderText("Account not found!");
    }

    public static void loadAccountsFromFile() {
        java.io.File file = new java.io.File("accounts.txt");
        if (!file.exists()) return;

        accounts.clear(); // Wipe the temporary list so we don't get duplicates

        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {
                    String name = data[0];
                    int accNum = Integer.parseInt(data[1]);
                    double balance = Double.parseDouble(data[2]);

                    // 1. Recreate the account structure using your existing Bank logic!
                    createAccount(accNum, name);

                    // 2. Add their money back if they had a balance
                    if (balance > 0) {
                        deposit(balance, accNum);
                    }
                }
            }
            System.out.println("📂 Permanent data loaded completely into active memory!");
        } catch (Exception e) {
            System.out.println("❌ Error loading data: " + e.getMessage());
        }
    }
}

