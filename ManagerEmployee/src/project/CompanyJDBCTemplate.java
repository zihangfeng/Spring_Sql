package project;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class CompanyJDBCTemplate implements CompanyDAO {
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplateObject;
   
   public void setDataSource(DataSource dataSource) 
   {
	   this.dataSource = dataSource;
	   this.jdbcTemplateObject = new JdbcTemplate(dataSource);
   }

   public void createEmployee(int eid, String name, int salary, int mid) 
   {
	   	String SQL = "select count(*) from employee where eid = ?";
	   	int count = jdbcTemplateObject.queryForInt(SQL, eid);
	   	if(count > 0)
	   		return;

	   	SQL = "insert into Employee (eid, name, salary) values (?, ?, ?)";      
	   	jdbcTemplateObject.update( SQL, eid, name, salary);
	   
	   	SQL = "insert into Manager (mid, eid) values (?, ?)";
	   	jdbcTemplateObject.update( SQL, mid, eid);
	   
	   	//System.out.println("Created employee record eid " + eid + " and name " + name + " salary = " + salary);
	   	return;
   }
   
   public void createManager(int mid, int eid)
   {
		String SQL = "select count(*) from manager where mid = ?";
		int count = jdbcTemplateObject.queryForInt(SQL, mid);
		   
		if(count > 0)
			 return;
		   
		SQL = "insert into Manager (mid, eid) values (?, ?)";
		      
		jdbcTemplateObject.update( SQL, mid, eid);
		//System.out.println("Created manager record mid: " + mid);
		return;	   
   }

   public Employee getEmployee(int eid) {
	   	String SQL = "select * from Employee where eid = ?";
	   	Employee employee = jdbcTemplateObject.queryForObject(SQL, 
                        new Object[]{eid}, new EmployeeMapper());
	   	return employee;
   }

   public Manager getManager(int mid) 
   {
	   	String SQL = "select * from Manager where mid = ?";
	   	Manager manager = jdbcTemplateObject.queryForObject(SQL, 
	                        new Object[]{mid}, new ManagerMapper());
	   	return manager;
   }
   
   public List<Employee> listEmployees() 
   {
	   	String SQL = "select * from Employee";
      	List <Employee> employees = jdbcTemplateObject.query(SQL, 
                                new EmployeeMapper());
      	return employees;
   }
   
   public int ListEmployeeCount(int mid)
   {
	   String SQL = "select count(*) from manager where mid = ?";
	   int count = jdbcTemplateObject.queryForInt(SQL, mid);
	   
	   return count;
   }
   
   public List<Manager> listManagers() 
   {
	    String SQL = "select * from Manager";
	    List <Manager> managers = jdbcTemplateObject.query(SQL, 
	                                new ManagerMapper());
	    return managers;
   }

   public void deleteEmployee(int eid)
   {
	   String SQL = "select count(*) from employee where eid = ?";
	   int count = jdbcTemplateObject.queryForInt(SQL, eid);
	   if(count < 1)
	   {
		   System.out.println("No such record exists");
		   return;
	   }
	   
	   //delete from the employee table
       SQL = "delete from Employee where eid = ?";
       jdbcTemplateObject.update(SQL, eid);
       
       // delete from the manager table
       SQL = "delete from Manager where eid = ?";
       jdbcTemplateObject.update(SQL, eid);
       
       System.out.println("Deleted Employee Record with eid = " + eid );
       return;
   }

   public void updateEmployee(int eid, String name, int salary)
   {
	   String SQL = "select count(*) from employee where eid = ?";
	   int count = jdbcTemplateObject.queryForInt(SQL, eid);
	   if(count < 1)
	   {
		   System.out.println("No such record exists");
		   return;
	   }
	   
	   SQL = "update Employee set name = ?, salary = ? where eid = ?";
	   jdbcTemplateObject.update(SQL, name, salary, eid);
	   System.out.println("Updated Record with ID = " + eid );
	   return;
   }
   
}