package Junit_Activities;

@SuppressWarnings("serial")
public class notEnoughFundsException extends RuntimeException {

    public notEnoughFundsException(Integer amount, Integer balance) {
        super("Attempted to withdraw " + amount + " with a balance of " + balance);
    }

}
