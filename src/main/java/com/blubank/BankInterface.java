package banking;

import java.util.Map;

/**
 * Bank interface defining the contract to interact with such an entity.
 */
public interface BankInterface {

    /**
     * Creates a new commercial account.
     *
     * @param company         The company associated with this account.
     * @param pin             Account PIN.
     * @param startingDeposit Initial funds deposited in this account.
     * @return The account number for the newly created account.
     */
    public Long openCommercialAccount(Company company, int pin, double startingDeposit);

    /**
     * Creates a new consumer account.
     *
     * @param person          The person associated with this account.
     * @param pin             Account PIN.
     * @param startingDeposit Initial funds deposited in this account.
     * @return The account number for the newly created account.
     */
    public Long openConsumerAccount(Person person, int pin, double startingDeposit);

    /**
     * Check if PIN matches the account.
     *
     * @param accountNumber The number of the account to be authenticated.
     * @param pin           Account PIN to be used.
     * @return <code>true</code> if authentication was successful; <code>false</code> otherwise.
     */
    public boolean authenticateUser(Long accountNumber, int pin);

    /**
     * Retrieve balance for designated account.
     *
     * @param accountNumber The number of the account.
     * @return the balance of the account.
     */
    public double getBalance(Long accountNumber);

    /**
     * Perform a credit in the designated account.
     *
     * @param accountNumber The number of the account to be credited.
     * @param amount        The amount of money being deposited.
     */
    public void credit(Long accountNumber, double amount);

    /**
     * Perform a debit in the given account, if possible.
     *
     * @param accountNumber The number of the account to be debited.
     * @param amount        The desired amount of the debit.
     * @return <code>true</code> if amount could be withdrawn; <code>false</code> otherwise.
     */
    public boolean debit(Long accountNumber, double amount);

    /**
     * Add new authorized user to a (commercial) account.
     *
     * @param accountNumber
     * @param authorizedPerson
     */
    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson);

    /**
     * Check user is authorized for designated (commercial) account.
     *
     * @param accountNumber
     * @param authorizedPerson
     * @return
     */
    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson);

    /**
     * Calculates the average account balance, grouped by Account type.
     * The keys in this map correspond to a string representation of the Account types that exist 
     * (CommercialAccount, ConsumerAccount), with the values being the result of the calculation 
     * of the average balance of all accounts of that type that are registered in the Bank.
     *
     * @return A Map with keys representing the Account type (e.g. 'ConsumerAccount', 'CommercialAccount')
     * and the values with the calculated average balance for those account types.
     */
    public Map<String, Double> getAverageBalanceReport();
}
