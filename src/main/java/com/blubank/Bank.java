package banking;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {
    private LinkedHashMap<Long, Account> accounts;
    private static Long CONSUMER_ACCOUNT_NUMBER = 5555L;

    public Bank() {
        accounts = new LinkedHashMap<>();
    }

    private Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        Account account = new CommercialAccount(company, ++CONSUMER_ACCOUNT_NUMBER, pin, startingDeposit);
        accounts.put(CONSUMER_ACCOUNT_NUMBER, account);
        return account.getAccountNumber();
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        Account account = new ConsumerAccount(person, ++CONSUMER_ACCOUNT_NUMBER, pin, startingDeposit);
        accounts.put(CONSUMER_ACCOUNT_NUMBER, account);
        return account.getAccountNumber();
    }

    public double getBalance(Long accountNumber) {
        if (!accounts.containsKey(accountNumber))
            return -1.0;
        return accounts.get(accountNumber).getBalance();
    }

    public void credit(Long accountNumber, double amount) {
        accounts.get(accountNumber).creditAccount(amount);
    }

    public boolean debit(Long accountNumber, double amount) {
        return accounts.get(accountNumber).debitAccount(amount);
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        return accounts.get(accountNumber).validatePin(pin);
    }
    
    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        CommercialAccount account = (CommercialAccount) accounts.get(accountNumber);
        account.addAuthorizedUser(authorizedPerson);
    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Account account = accounts.get(accountNumber);
        if (account instanceof CommercialAccount) {
            CommercialAccount commercialAccount = (CommercialAccount) account;
            return commercialAccount.isAuthorizedUser(authorizedPerson);
        }
        return false;
    }

    public Map<String, Double> getAverageBalanceReport() {
        Map<String, Double> averageBalances = new LinkedHashMap<>();
        double[] sumConsumer = new double[1];
        double[] sumCumercial = new double[1];
        int[] counter1 = new int[1];
        int[] counter2 = new int[1];
        accounts.forEach((id, account) -> {
            if (account instanceof ConsumerAccount) {
                sumConsumer[0] += account.getBalance();
                counter1[0]++;
            } else {
                sumCumercial[0] += account.getBalance();
                counter2[0]++;
            }
        });
        averageBalances.put(ConsumerAccount.class.getSimpleName(), sumConsumer[0] / counter1[0]);
        averageBalances.put(CommercialAccount.class.getSimpleName(), sumCumercial[0] / counter2[0]);
        return averageBalances;
    }
}
