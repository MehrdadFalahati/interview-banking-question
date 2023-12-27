package com.blubank;

/**
 * Abstract bank account.
 */
public abstract class Account implements AccountInterface {
    private AccountHolder accountHolder;
    private Long accountNumber;
    private int pin;
    private double balance;

    protected Account(AccountHolder accountHolder, Long accountNumber, int pin, double startingDeposit) {

    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public boolean validatePin(int attemptedPin) {
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void creditAccount(double amount) {

    }

    public boolean debitAccount(double amount) {
        return false;
    }
}
