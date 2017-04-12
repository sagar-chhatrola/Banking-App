package pojo;

public class Transaction {
	int transactionId;
	int amount;
	int creditedAccount;
	int debitedAccount;
	String datetime;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCreditedAccount() {
		return creditedAccount;
	}
	public void setCreditedAccount(int creditedAccount) {
		this.creditedAccount = creditedAccount;
	}
	public int getDebitedAccount() {
		return debitedAccount;
	}
	public void setDebitedAccount(int debitedAccount) {
		this.debitedAccount = debitedAccount;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	
}
