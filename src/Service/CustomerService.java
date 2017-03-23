package Service;

import java.sql.SQLException;

public interface CustomerService {

	public  int accountValidation(int id,int acc_no) throws SQLException;
    public  int accountValidation1(int id,int acc_no_transfer) throws SQLException;
    public  int Transfer_ammount(Integer ammount,Integer acc_no,Integer account_transfer,int id) throws SQLException;

}
