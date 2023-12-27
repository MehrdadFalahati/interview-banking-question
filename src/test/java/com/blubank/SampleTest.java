package banking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SampleTest {
    BankInterface bank;
    Long consumerAccountNr1;
    Long consumerAccountNr2;
    Long consumerAccountNr3;
    Long consumerAccountNr4;
    Long commercialAccountNr1;
    Long commercialAccountNr2;
    Person person1;
    Person person2;
    Person person3;

    @Before
    public void setUp() {
        bank = new Bank();
        person1 = new Person("John", "Doe", 1);
        person2 = new Person("Julia", "Doe", 2);
        person3 = new Person("Daniel", "Smith", 3);
        consumerAccountNr1 = bank.openConsumerAccount(person1, 1111, 0.0);
        consumerAccountNr2 = bank.openConsumerAccount(person2, 2222, 250.00);
        consumerAccountNr3 = bank.openConsumerAccount(person3, 3333, 600.00);
        consumerAccountNr4 = bank.openConsumerAccount(person3, 4444, 300.00);

        Company company1 = new Company("BigCorp1", 1);
        Company company2 = new Company("BigCorp2", 2);
        commercialAccountNr1 = bank.openCommercialAccount(company1, 1111, 0.0);
        commercialAccountNr2 = bank.openCommercialAccount(company2, 2222, 12345.00);

        bank.addAuthorizedUser(commercialAccountNr1, person1);
        bank.addAuthorizedUser(commercialAccountNr1, person2);
        bank.addAuthorizedUser(commercialAccountNr2, person3);
    }

    @After
    public void tearDown() throws Exception {
        bank = null;
        consumerAccountNr1 = null;
        consumerAccountNr2 = null;
        consumerAccountNr3 = null;
        commercialAccountNr1 = null;
        commercialAccountNr2 = null;
    }

    @Test
    public void sequentialAccountNumberTest() {
        assertEquals(consumerAccountNr1 + 1, (long) consumerAccountNr2);
        assertEquals(consumerAccountNr2 + 1, (long) consumerAccountNr3);
        assertEquals(consumerAccountNr4 + 1, (long) commercialAccountNr1);
        assertEquals(commercialAccountNr1 + 1, (long) commercialAccountNr2);
    }

    @Test
    public void validBalanceTest() {
        assertEquals(bank.getBalance(consumerAccountNr1), 0.0, 0);
        assertEquals(bank.getBalance(consumerAccountNr2), 250.00, 0);
        assertEquals(bank.getBalance(consumerAccountNr3), 600.00, 0);
        assertEquals(bank.getBalance(commercialAccountNr1), 0.0, 0);
        assertEquals(bank.getBalance(commercialAccountNr2), 12345.00, 0);
    }

    /**
     * Valid account debit.
     */
    @Test
    public void validDebitTest() {
        double amount = 200.0;
        assertTrue("Account " + consumerAccountNr2 + " should have sufficient funds.", bank.debit(consumerAccountNr2, amount));
        assertTrue("Account " + consumerAccountNr3 + " should have sufficient funds.", bank.debit(consumerAccountNr3, amount));
        assertTrue("Account " + commercialAccountNr2 + " should have sufficient funds.", bank.debit(commercialAccountNr2, amount));
    }

    /**
     * Test crediting accounts inside {@link Bank}.
     */
    @Test
    public void creditAccountTest() {
        double amount = 500.00;
        double initialBalance1 = bank.getBalance(consumerAccountNr1);
        double initialBalance2 = bank.getBalance(consumerAccountNr2);
        double initialBalance3 = bank.getBalance(consumerAccountNr3);
        double initialBalance4 = bank.getBalance(commercialAccountNr1);
        double initialBalance5 = bank.getBalance(commercialAccountNr2);

        bank.credit(consumerAccountNr1, amount);
        bank.credit(consumerAccountNr2, amount);
        bank.credit(consumerAccountNr3, amount);
        bank.credit(commercialAccountNr1, amount);
        bank.credit(commercialAccountNr2, amount);

        assertEquals(initialBalance1 + amount, bank.getBalance(consumerAccountNr1), 0);
        assertEquals(initialBalance2 + amount, bank.getBalance(consumerAccountNr2), 0);
        assertEquals(initialBalance3 + amount, bank.getBalance(consumerAccountNr3), 0);
        assertEquals(initialBalance4 + amount, bank.getBalance(commercialAccountNr1), 0);
        assertEquals(initialBalance5 + amount, bank.getBalance(commercialAccountNr2), 0);
    }

    @Test
    public void transactionDebitTest() throws Exception {
        Transaction transaction1 = new Transaction(bank, consumerAccountNr3, 3333);
        double beforeDeposit1 = transaction1.getBalance();
        double amount = 500.0;

        assertTrue("Debit was unsuccessful.", transaction1.debit(amount));
        assertFalse("This transaction should have overdrawn the account.", transaction1.debit(amount));
        assertTrue("Debit was unsuccessful.", transaction1.debit(transaction1.getBalance()));
        assertEquals(0, transaction1.getBalance(), 0);
    }

    @Test
    public void transactionCreditTest() throws Exception {
        Transaction transaction1 = new Transaction(bank, consumerAccountNr3, 3333);
        double beforeDeposit1 = transaction1.getBalance();
        double amount = 9999999.0;
        transaction1.credit(amount);
        assertEquals(beforeDeposit1 + amount, transaction1.getBalance(), 0);
    }
}
