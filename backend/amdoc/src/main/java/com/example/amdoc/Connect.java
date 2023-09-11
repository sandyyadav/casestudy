package com.example.amdoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connect {
	Connection con;
	public Connect()
	{
		try{  
			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			  
			//step2 create  the connection object  
			 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","12345");
			// Statement smtm=con.createStatement();
			  
			}
		catch(Exception e)
		{ System.out.println(e);} 
	}
	
}

