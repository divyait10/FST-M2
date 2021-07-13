package Junit_Activities;

public class bankAccount {
    
	private Integer balance;
    public bankAccount(Integer initialBalance) {
    balance = initialBalance;
    }

    public Integer withdraw(Integer amount) {
        if (balance < amount) {
            throw new notEnoughFundsException(amount, balance);
        }
        balance -= amount;
        return balance;
    }
}