package com.gabriel.twoforms;

import javafx.scene.control.Alert;

public class Account {
    private int accountNumber;
    private String owner;
    private double balance;

    public Account(int accountNumber, String owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0.0;
    }

    public int getAccountNumber() { return accountNumber; }
    public String getOwner() { return owner; }
    public double getBalance() { return balance; }

    public void deposit(double DepositedAmount) {
        Alert AlertDeposit = new Alert(Alert.AlertType.INFORMATION);
        AlertDeposit.setHeaderText("Invalid deposit amount.");
        AlertDeposit.setTitle("Deposit Warning!");

        Alert DepositAlert = new Alert(Alert.AlertType.INFORMATION);
        DepositAlert.setHeaderText("Deposited: " + DepositedAmount);
        DepositAlert.setTitle("Deposit Warning!");

        if (DepositedAmount > 0) {
            balance += DepositedAmount;
            System.out.println("Deposited: " + DepositedAmount);
            DepositAlert.show();
        } else {
            System.out.println("Invalid deposit amount.");
            AlertDeposit.show();
        }
    }

    public void withdraw(double WithdrawAmount) {
        Alert AlertWithdraw = new Alert(Alert.AlertType.INFORMATION);
        AlertWithdraw.setHeaderText("Invalid Withdrawal amount.");
        AlertWithdraw.setTitle("Withdraw Warning!");

        Alert WithdrawAlert = new Alert(Alert.AlertType.INFORMATION);
        WithdrawAlert.setHeaderText("Withdrawn: " + WithdrawAmount);
        WithdrawAlert.setTitle("Withdraw Warning!");

        if (WithdrawAmount > 0 && WithdrawAmount < balance) {
            balance -= WithdrawAmount;
            System.out.println("Withdrawn: " + WithdrawAmount);
            WithdrawAlert.show();
        } else {
            System.out.println("Invalid Withdrawal amount.");
            AlertWithdraw.show();
        }
    }

    // Silent version — adds balance directly with no Alert popup.
    // Used when restoring saved balances on startup (e.g. Bank.loadAccountsFromFile).
    public void depositSilently(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void CheckBal(double CheckB) {
        Alert AlertCheckBalance = new Alert(Alert.AlertType.INFORMATION);
        AlertCheckBalance.setHeaderText("Here is your Balance in your account.");
    }
}    }

    public void withdraw(double WithdrawAmount) {
        Alert AlertWithdraw = new Alert(Alert.AlertType.INFORMATION);
        AlertWithdraw.setHeaderText("Invalid Withdrawal amount.");
        AlertWithdraw.setTitle("Withdraw Warning!");

        Alert WithdrawAlert = new Alert(Alert.AlertType.INFORMATION);
        WithdrawAlert.setHeaderText("Withdrawn: " + WithdrawAmount);
        WithdrawAlert.setTitle("Withdraw Warning!");

        if (WithdrawAmount > 0 && WithdrawAmount < balance) {
            balance -= WithdrawAmount;
            System.out.println("Withdrawn: " + WithdrawAmount);
            WithdrawAlert.show();
        } else {
            System.out.println("Invalid Withdrawal amount.");
            AlertWithdraw.show();
        }
    }

    public void CheckBal(double CheckB) {
        Alert AlertCheckBalance = new Alert(Alert.AlertType.INFORMATION);
        AlertCheckBalance.setHeaderText("Here is your Balance in your account.");
    }
}
