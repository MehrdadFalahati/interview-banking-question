package banking;

/**
 * The public contract for an Account.
 */
public interface AccountInterface {

    /**
     * Validate the PIN.
     *
     * @param attemptedPin The attempted PIN.
     * @return <code>true</code> if attemptedPin matches the account; <code>false</code> otherwise.
     */
    public boolean validatePin(int attemptedPin);

    /**
     * Add amount to account balance.
     *
     * @param amount The amount to be deposited into the account.
     */
    public void creditAccount(double amount);

    /**
     * Withdraw amount from account (if possible).
     *
     * @param amount The amount to be withdrawn from the account.
     * @return <code>true</code>  if amount could be withdrawn; <code>false</code> otherwise.
     */
    public boolean debitAccount(double amount);
}
