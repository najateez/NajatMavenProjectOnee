package com.najatmavenprojectpkg;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class JsonApiTable {
	
	//json which is given put it in json formatter -> take values from json used api -> save api values in db.
	
		public static void isApiJsonTableCreated() {
			
			String url = "jdbc:mysql://localhost:3306/apidb";
			 String user = "root";
		     String pass = "10@104Ar$"; 
				
			String apiTable = "CREATE TABLE Api (" 
			        + "api_id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,"  
			        + "web_pages VARCHAR(100)," 
			        + "state_province VARCHAR(100),"
			        + "alpha_two_code VARCHAR(100),"
			        + "name VARCHAR(100),"
			        + "country VARCHAR(100),"
			        + "domains VARCHAR(100))"; 
			
		        Connection con = null;
		        
		        try {

		            Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
		            DriverManager.registerDriver(driver);
		            
		            con = DriverManager.getConnection(url, user,pass);
		            Statement st = con.createStatement();

		            int m = st.executeUpdate(apiTable);
		            if (m >=  1) {
		                System.out.println("table api created successfully : " + apiTable);
		                
		            }
		            else {
		                System.out.println("table api not created, try again");
		            }
		            con.close();
		        } catch (Exception ex) {
		            System.err.println(ex);
		  }
		        
		}

	public static void main(String[] args) {
		
		isApiJsonTableCreated();
		

	}

}
