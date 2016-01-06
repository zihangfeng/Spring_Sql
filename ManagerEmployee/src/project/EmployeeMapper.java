package project;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeMapper implements RowMapper<Employee>{
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
	      Employee employee = new Employee();
	      employee.setEid(rs.getInt("eid"));
	      employee.setEmployeeName(rs.getString("name"));
	      employee.setSalary(rs.getInt("salary"));
	      return employee;
	   }
}
