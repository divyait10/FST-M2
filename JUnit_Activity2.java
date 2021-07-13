package Junit_Activities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JUnit_Activity2 {

    @Test
    void notEnoughFunds() {
        bankAccount account = new bankAccount(9);
        assertThrows(notEnoughFundsException.class, () -> account.withdraw(10),
                "Balance must be greater than amount of withdrawal");
    }

    @Test
    void enoughFunds() {
        bankAccount account = new bankAccount(100);
        assertDoesNotThrow(() -> account.withdraw(100));
    }
}