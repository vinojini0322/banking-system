package BankAccount;

public class AccountDto {
    private long customerId;
    private long accountNumber;
    private double balance;
    private double withdrawel;
    private double deposit;
    private double previousTransaction;

    public AccountDto(long customerId, long accountNumber, double balance) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getWithdrawel() {
        return withdrawel;
    }

    public void setWithdrawel(double withdrawel) {
        this.withdrawel = withdrawel;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getPreviousTransaction() {
        return previousTransaction;
    }

    public void setPreviousTransaction(double previousTransaction) {
        this.previousTransaction = previousTransaction;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
               "customerId=" + customerId +
               ", accountNumber=" + accountNumber +
               ", balance=" + balance +
               ", withdrawel=" + withdrawel +
               ", deposit=" + deposit +
               ", previousTransaction=" + previousTransaction +
               '}';
    }
}
