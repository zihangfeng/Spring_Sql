package project;

import java.util.List;
import javax.sql.DataSource;


public interface CompanyDAO {
	
	// database resource initialization connection 
	public void setDataSource(DataSource ds);

	// to create an employee record
	public void createEmployee(int eid, String name, int salary, int mid);
	
	// to create an manager record
	public void createManager(int mid, int eid);
	
	// get an employee record
	public Employee getEmployee(int eid);
	
	// get a manager record
	public Manager getManager(int mid);
	
	// list all employees
	public List<Employee> listEmployees();
	
	// list the number of employees under this manager
	public int ListEmployeeCount(int mid);
	
	// list all managers
	public List<Manager> listManagers();
	
	// delete an employee
	public void deleteEmployee(int eid);
	
	// update an employee
	public void updateEmployee(int eid, String name, int salary);
	
}
