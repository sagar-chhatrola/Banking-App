package pojo;

public class Transaction {
int t_id;
int amount;
int credited_acc;
int debited_acc;
String datetime;

public int getT_id() {
	return t_id;
}
public void setT_id(int t_id) {
	this.t_id = t_id;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public int getCredited_acc() {
	return credited_acc;
}
public void setCredited_acc(int credited_acc) {
	this.credited_acc = credited_acc;
}
public int getDebited_acc() {
	return debited_acc;
}
public void setDebited_acc(int debited_acc) {
	this.debited_acc = debited_acc;
}
public String getDatetime() {
	return datetime;
}
public void setDatetime(String datetime) {
	this.datetime = datetime;
}
}
