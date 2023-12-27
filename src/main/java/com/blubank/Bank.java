package com.blubank;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {

    public Bank() {

    }

    private Account getAccount(Long accountNumber) {
        return null;
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        return 0L;
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        return 0L;
    }

    public double getBalance(Long accountNumber) {
        return 0.0d;
    }

    public void credit(Long accountNumber, double amount) {

    }

    public boolean debit(Long accountNumber, double amount) {
        return false;
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        return false;
    }
    
    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {

    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        return false;
    }

    public Map<String, Double> getAverageBalanceReport() {
        return null;
    }
}
