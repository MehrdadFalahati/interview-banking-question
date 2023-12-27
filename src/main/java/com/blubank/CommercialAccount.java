package com.blubank;

/**
 * Account implementation for commercial (business) customers.
 * The account's holder is a {@link Company}.
 */
public class CommercialAccount extends Account {

    public CommercialAccount(Company company, Long accountNumber, int pin, double startingDeposit) {
        super(company, accountNumber, pin, startingDeposit);
    }

    /**
     * Add person to list of authorized users.
     *
     * @param person The authorized user to be added to the account.
     */
    protected void addAuthorizedUser(Person person) {

    }

    /**
     * Verify if the person is part of the list of authorized users for this account.
     *
     * @param person
     * @return <code>true</code> if person matches an authorized user in {@link #authorizedUsers}; <code>false</code> otherwise.
     */
    public boolean isAuthorizedUser(Person person) {
        return false;
    }
}
