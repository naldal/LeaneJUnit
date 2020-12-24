package chapter3.HancrestTest;

import chapter3.Account;
import chapter3.InsufficientFundsException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.fail;

public class AssertTest {
    private Account account;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setup() {
        account = new Account("");
    }

    @Test(expected = InsufficientFundsException.class)
    public void throwsWhenWithdrawingTooMuch(){
        account.withdraw(100);
    }

    @Test
    public void exceptionRule() {
        thrown.expect(InsufficientFundsException.class);
        thrown.expectMessage("balance only 0");

        account.withdraw(100);
    }
}
