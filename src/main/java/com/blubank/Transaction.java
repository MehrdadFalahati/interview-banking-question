package com.blubank;

/**
 * A bank transaction implementation.
 */
public class Transaction implements TransactionInterface {

    /**
     * @param bank          The bank where the account is housed.
     * @param accountNumber The customer's account number.
     * @param attemptedPin  The PIN entered by the customer.
     * @throws Exception Account validation failed.
     */
    public Transaction(BankInterface bank, Long accountNumber, int attemptedPin) throws Exception {

    }

    public double getBalance() {
        return 0.0d;
    }

    public void credit(double amount) {

    }

    public boolean debit(double amount) {
       return false;
    }
}
