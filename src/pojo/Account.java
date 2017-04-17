package pojo;

public class Account {
	public int accountNumber;
	public long balance;
	public boolean approve;

	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
	}

	public int getaccountNumber() {
		return accountNumber;
	}

	public void setaccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
}
