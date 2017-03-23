package test;

import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;

import com.aimdek.dao.EmployeeDaoImpl;
import com.aimdek.entity.Employee;

public class TestService {
	@Before
	public void setUp() throws Exception {
		employeeDaoMock = mock(EmployeeDaoImpl.class);
		employee = new Employee();
		employee.setId(5);
		employee.setName("Meera");
		employee.setSalary(new Double(50000));
		employee.setAge(24);
	}
	@After
	public void tearDown() throws Exception {
		 employeeDaoMock=null;
		 employee=null;
	}
}
