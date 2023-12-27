package banking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Account implementation for commercial (business) customers.
 * The account's holder is a {@link Company}.
 */
public class CommercialAccount extends Account {
    private List<Person> authorizedUsers;

    public CommercialAccount(Company company, Long accountNumber, int pin, double startingDeposit) {
        super(company, accountNumber, pin, startingDeposit);
        this.authorizedUsers = new ArrayList<>();
    }

    /**
     * Add person to list of authorized users.
     *
     * @param person The authorized user to be added to the account.
     */
    protected void addAuthorizedUser(Person person) {
        authorizedUsers.add(person);
    }

    /**
     * Verify if the person is part of the list of authorized users for this account.
     *
     * @param person
     * @return <code>true</code> if person matches an authorized user in {@link #authorizedUsers}; <code>false</code> otherwise.
     */
    public boolean isAuthorizedUser(Person person) {
        List<Person> isAuthorize = authorizedUsers.stream()
                .filter(p -> p.equals(person))
                .collect(Collectors.toList());
        return !isAuthorize.isEmpty();
    }
}
