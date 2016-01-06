package project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int eid, mid, salary;
		String name;
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		CompanyJDBCTemplate companyJDBCTemplate = (CompanyJDBCTemplate)context.getBean("companyJDBCTemplate");
		
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new FileInputStream("employeesFile.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found or cound not be open!");
			System.exit(0);
		}
		
	    System.out.println("Create Employees");
		while(inputStream.hasNextLine())
		{
			StringTokenizer tokens = new StringTokenizer(inputStream.nextLine());
			eid = Integer.parseInt(tokens.nextToken());
			name = tokens.nextToken();
			salary = Integer.parseInt(tokens.nextToken());
			mid = Integer.parseInt(tokens.nextToken());
			companyJDBCTemplate.createEmployee(eid, name, salary, mid);
		}
		inputStream.close();
		System.out.println("Finished createing emploees from the file!");
		
		while(true)
		{
			System.out.println("----- Options to query the database -----");
			System.out.println("Option 1: to insert a new employee\n"
							  +"Option 2: to find an employee\n"
							  +"Option 3: to find an manager with whom work under him\n"
							  +"Option 4: to modify an employee\n"
							  +"Option 5: to detele an employee\n"
							  +"Option 6: list all employees\n"
							  +"Option 7: list all managers\n"
							  +"Option 8: to quit the program");
			
			
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter a number: ");
			int n = reader.nextInt();
			if(n == 1)
			{
				System.out.println("You try to insert a new employee\n"
								  +"You need to provide his eid, name, salary, and manager mid his works for!");
				
				System.out.println("eid: ");
				reader = new Scanner(System.in);
				eid = reader.nextInt();
				
				System.out.println("employee name: ");
				reader = new Scanner(System.in);
				name = reader.nextLine();
				
				System.out.println("salary: ");
				reader = new Scanner(System.in);
				salary = reader.nextInt();
				
				System.out.println("manager mid: ");
				reader = new Scanner(System.in);
				mid = reader.nextInt();
				
				companyJDBCTemplate.createEmployee(eid, name, salary, mid);
			}
			else if(n == 2)
			{
				System.out.println("Finding an employee requires his eid!");
				System.out.println("eid: ");
				reader = new Scanner(System.in);
				eid = reader.nextInt();
				
				Employee emp = companyJDBCTemplate.getEmployee(eid);
			    System.out.print("eid : " + emp.getEid() );
			    System.out.print(", Name : " + emp.getEmployeeName() );
			    System.out.println(", Salary : " + emp.getSalary());
				
			}
			else if(n == 3)
			{
				System.out.println("Find the number of employees works for under a particular manager");
				System.out.println("mid: ");
				reader = new Scanner(System.in);
				mid = reader.nextInt();
				int count = companyJDBCTemplate.ListEmployeeCount(mid);
				System.out.println("Number of employees work for this manager " + mid + " is " + count);
			}
			else if(n == 4)
			{
				System.out.println("Modify an existing employee");
				
				System.out.println("eid: ");
				reader = new Scanner(System.in);
				eid = reader.nextInt();
				
				System.out.println("employee name: ");
				reader = new Scanner(System.in);
				name = reader.nextLine();
				
				System.out.println("salary: ");
				reader = new Scanner(System.in);
				salary = reader.nextInt();
				
				companyJDBCTemplate.updateEmployee(eid, name, salary);
				
			}
			else if(n == 5)
			{
				System.out.println("Delete an emploee.");
				
				System.out.println("eid: ");
				reader = new Scanner(System.in);
				eid = reader.nextInt();
				
				companyJDBCTemplate.deleteEmployee(eid);
			}
			else if(n == 6)
			{
				System.out.println("List all available employees" );
			    List<Employee> employees = companyJDBCTemplate.listEmployees();
			    for (Employee emp : employees) {
			         System.out.print("eid : " + emp.getEid() );
			         System.out.print(", Name : " + emp.getEmployeeName() );
			         System.out.println(", Salary : " + emp.getSalary());
			      }
			}
			else if(n == 7)
			{
			    System.out.println("List all available managers" );
			    List<Manager> managers = companyJDBCTemplate.listManagers();
			    for (Manager mng : managers) {
			         System.out.print("mid : " + mng.getMid() );
			         System.out.println(", eid : " + mng.getManagerEid() );
			    }
			}
			else if(n == 8)
			{
				System.out.println("System quit!");
				break;
			}
			else
			{
				System.out.println("You have typed something different than the expected value, type again!");
			}
				
			System.out.println("");
			
		}
		
		
	}

}
