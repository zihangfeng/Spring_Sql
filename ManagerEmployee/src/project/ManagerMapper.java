package project;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ManagerMapper implements RowMapper<Manager>{
	public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
		  Manager manager = new Manager();
		  manager.setMid(rs.getInt("mid"));
		  manager.setManagerEid(rs.getInt("eid"));
	      return manager;
	   }
}