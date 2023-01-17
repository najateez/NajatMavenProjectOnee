package com.najatmavenprojectpkg;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Main {
	
	// insert json values by api in db (table).
	
	public static void insertValuesByApiInDB() throws IOException, InterruptedException { //can use try and catch or this throws keyword
		
		//URL->Response->String->Define object-> output use (for each).
		
		String url = "jdbc:mysql://localhost:3306/apidb";
		String user = "root";
	    String pass = "10@104Ar$";
	    
	    HttpClient hClient = HttpClient.newHttpClient();
		HttpRequest requestData = HttpRequest.newBuilder()
				.uri(URI.create("http://universities.hipolabs.com/search?country=United+States"))
				.build();
		
		HttpResponse<String> response = hClient.send(requestData, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		
		//https://mkyong.com/java/how-to-enable-pretty-print-json-output-gson/
		
	//	Gson gson=new Gson(); //print all data in one line then insert
		
		//Pretty-Print JSON in Java. from this website: https://www.tabnine.com/code/java/methods/com.google.gson.JsonParser/parse
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); //print all data line by line in pretty shape as json shape first then insert.
		
		JsonParser jParser = new JsonParser(); //import from library
		JsonElement jElement = jParser.parse(response.body());   //previous string (up) that json shape. import library 
		String jsonString = gson.toJson(jElement);
		System.out.println(jsonString);
		
		JsonApi[] data = gson.fromJson(jsonString, JsonApi[].class);  //to take data from JsonApi class
		
		for (JsonApi x : data) {    //for-each
			String web_pages = x.getWeb_pages()[0];
			String state_province = x.getState_province();
			String alpha_two_code = x.getAlpha_two_code();
			String name = x.getName();
			String country = x.getCountry();
			String domians = x.getDomains()[0];
			
			String SqlQueryForInserting =
					"INSERT INTO Api(web_pages,state_province,alpha_two_code,name,country,domains) VALUES ('" + web_pages + "' ,'" + state_province + "', '" + alpha_two_code+"','" + name + "' ,' " +country +  "','" +domians+"')";
			
	//		System.out.println(SqlQueryForInserting);
			
			    Connection con = null;
			try {
				 Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
				 DriverManager.registerDriver(driver);
				 con = (Connection) DriverManager.getConnection(url, user, pass);
				 Statement st = con.createStatement();
				 
				// Executing query
				int m = st.executeUpdate(SqlQueryForInserting);
				if (m >= 0)
					System.out.println("inserted successfully : " + SqlQueryForInserting);
				else
					System.out.println("insertion failed");
				// Closing the connections
				con.close();
			} catch (Exception ex) {
				System.err.println(ex);
		}
		}
	}
	
	
	public static void readValuesFromDbApi() throws IOException, InterruptedException {
		
		String url = "jdbc:mysql://localhost:3306/apidb";
		String user = "root";
	    String pass = "10@104Ar$";
	    
	    HttpClient hClient = HttpClient.newHttpClient();
		HttpRequest requestData = HttpRequest.newBuilder()
				.uri(URI.create("http://universities.hipolabs.com/search?country=United+States"))
				.build();
		
		HttpResponse<String> response = hClient.send(requestData, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());
		
       Gson gson = new GsonBuilder().setPrettyPrinting().create(); //print all data line by line in pretty shape as json shape first then insert.
		
		JsonParser jParser = new JsonParser();
		JsonElement jElement = jParser.parse(response.body());   
		String jsonString = gson.toJson(jElement);
		System.out.println(jsonString);
		
		JsonApi[] data = gson.fromJson(jsonString, JsonApi[].class);
		
	    Connection con = null;
	    
	    try {
			 Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			 DriverManager.registerDriver(driver);
			 con = (Connection) DriverManager.getConnection(url, user, pass);
			 Statement st = con.createStatement();
			 
			 String sql = "SELECT * FROM Api";
			 ResultSet rs = st.executeQuery(sql);
			 
			 for (JsonApi x : data) {    //for-each
				    String web_pages = x.getWeb_pages()[0];
					String state_province = x.getState_province();
					String alpha_two_code = x.getAlpha_two_code();
					String name = x.getName();
					String country = x.getCountry();
					String domains = x.getDomains()[0];
					
					System.out.println("web pages:" + web_pages +", state_province: " + state_province +", alpha_two_code:" + alpha_two_code + ", name:" + name + ", country:" + country + " "+ ", domains:" + domains + " ");
			 }
			 }catch (Exception ex) {
					System.err.println(ex);
				}
		}
	
	public static void deleteByIdInDbApi() throws IOException, InterruptedException {
		
		String url = "jdbc:mysql://localhost:3306/apidb";
		String user = "root";
	    String pass = "10@104Ar$";
	    
	    HttpClient hClient = HttpClient.newHttpClient();
		HttpRequest requestData = HttpRequest.newBuilder()
				.uri(URI.create("http://universities.hipolabs.com/search?country=United+States"))
				.build();
		
		HttpResponse<String> response = hClient.send(requestData, HttpResponse.BodyHandlers.ofString());
	//	System.out.println(response.body());
		
		Gson gson=new Gson();
		
		JsonParser jParser = new JsonParser();
		JsonElement jElement = jParser.parse(response.body());   
		String jsonString = gson.toJson(jElement);
		System.out.println(jsonString);
         JsonApi[] data = gson.fromJson(jsonString, JsonApi[].class);
		
	    Connection con = null;
	    
	    try {
			 Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			 DriverManager.registerDriver(driver);
			 con = (Connection) DriverManager.getConnection(url, user, pass);
			 Statement st = con.createStatement();
			 
			 Scanner in=new Scanner(System.in);
			 
			 System.out.println("enter id you want to delete:");
	        	int api_id=in.nextInt();
	        	
	        	String sql = "delete from Api where api_id = ?";
	        	
	        	 PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(sql);
	             preparedStmt.setInt(1, api_id);
	             // execute the preparedstatement
	             preparedStmt.execute();
	             con.close(); 
	          }
	       catch (Exception ex) {
	           System.err.println(ex);
	         }
	      }
	
	public static void updateByIdInDbApi() {
		
		String url = "jdbc:mysql://localhost:3306/apidb";
		String user = "root";
	    String pass = "10@104Ar$";
	    
	    HttpClient hClient = HttpClient.newHttpClient();
		HttpRequest requestData = HttpRequest.newBuilder()
				.uri(URI.create("http://universities.hipolabs.com/search?country=United+States"))
				.build();
		
		HttpResponse<String> response=null;
		
		try {
			response = hClient.send(requestData, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Gson gson=new Gson();
		
		JsonParser jParser = new JsonParser();
		JsonElement jElement = jParser.parse(response.body());   
		String jsonString = gson.toJson(jElement);
		System.out.println(jsonString);
         JsonApi[] data = gson.fromJson(jsonString, JsonApi[].class);
         
         Connection con = null;
 	    
 	    try {
 			 Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
 			 DriverManager.registerDriver(driver);
 			 con = (Connection) DriverManager.getConnection(url, user, pass);
 			 Statement st = con.createStatement();
 			 
 			 Scanner in=new Scanner(System.in);
 			 
 			 System.out.println("Enter the id of the row to update:");
 	        	int api_id=in.nextInt();
 	        	
 	        	String sql = "update Api set web_pages = ?, state_province = ?, alpha_two_code =?, name =?, country=?, domains=? where api_id = ?";
 				PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
 				
 				System.out.println("enter web_pages to be updated:");
 				String web_pages=in.next();
 				System.out.println("enter state_province to be updated:");
 				String state_province=in.next();
 				System.out.println("enter alpha_two_code to be updated:");
 				String alpha_two_code=in.next();
 				System.out.println("enter name to be updated:");
 				String name=in.next();
 				System.out.println("enter country to be updated:");
 				String country=in.next();
 				System.out.println("enter domains to be updated:");
 				String domains=in.next();
 				
 				pstmt.setString(1,web_pages);
 				pstmt.setString(2,state_province);
 				pstmt.setString(3,alpha_two_code);
 				pstmt.setString(4,name);
 				pstmt.setString(5,country);
 				pstmt.setString(6,domains);
 				pstmt.setInt(7,api_id);
 				pstmt.executeUpdate();
 				
 				String sql2 = "SELECT * FROM Api WHERE api_id = ?";
 				PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement(sql2);
 				pstmt2.setInt(1, api_id);
 				ResultSet rs = pstmt2.executeQuery();
 				if (rs.next()) {
 					String web_pagess = rs.getString("web_pages");
 					String state_provincee = rs.getString("state_province");
 					String alpha_two_codee = rs.getString("alpha_two_code");
 					String namee = rs.getString("name");
 					String countryy = rs.getString("country");
 					String domainss = rs.getString("domains");
 					
 					System.out.println("api_id:"+api_id + ", web_pages:" + web_pagess + ", state_province:" + state_provincee + ", alpha_two_code:" + alpha_two_codee + ", name:" + namee + ", country:" + countryy+ ", domains:" + domainss);
 				}
 			} catch (Exception e) {
 				System.out.println(e);
 			} 
 		}

	
	public static void main(String[] args) throws IOException, InterruptedException { //can use try and catch or this throws keyword
		
		Scanner in = new Scanner(System.in);
		
		while(true) {
		System.out.println(" Json->api->save the data to db ");
		System.out.println("*******************************");
		System.out.println("Menu:");
		System.out.println("0:insert values of json by api in db table.");
		System.out.println("1:read values from db (Api).");
		System.out.println("2:delete by id in db (Api).");
		System.out.println("3:update by id in db (Api).");
		System.out.println("4:Exit.");
		System.out.println("*******************************");
		System.out.println("Enter a number from menu: ");
		int choice=in.nextInt();
		
		switch(choice) {
		case 0:{
		  insertValuesByApiInDB();
		  System.out.println("*******************************");
		  break;
		}case 1:{
		  readValuesFromDbApi();
		  System.out.println("*******************************");
		  break;
		}case 2:{
		  deleteByIdInDbApi();
		  System.out.println("*******************************");
		  break;
		}case 3:{
		  updateByIdInDbApi();	
		  System.out.println("*******************************");
		  break;
		}case 4:{
			return;
		}default:{
			System.out.println("it is not an option, try again.");
			System.out.println("*******************************");
			break;
		}
		}}
			

	}

}
