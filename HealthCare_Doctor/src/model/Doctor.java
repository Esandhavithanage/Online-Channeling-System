package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/helthcare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			System.out.println("Database Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

	public String AddDoctor(String D_fname, String D_lname, String D_gender, int D_age, String D_docNIC,
			String D_docEmail, String D_password, int D_phonenumber ) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into doctor(`fName`,`lNmae`,`gender`,`age`,`docNIC`,`docEmail`,`passwod`,`phoneNumber`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, D_fname);
			preparedStmt.setString(2, D_lname);
			preparedStmt.setString(3, D_gender);
			preparedStmt.setInt(4, D_age);
			preparedStmt.setString(5, D_docNIC);
			preparedStmt.setString(6, D_docEmail);
			preparedStmt.setString(7, D_password);
			preparedStmt.setInt(8, D_phonenumber);


			
			// execute the statement
						preparedStmt.execute();
						con.close();
						output = "Inserted successfully";
					} catch (Exception e) {
						output = "Error while inserting the item.";
						System.err.println(e.getMessage());
					}
					return output;
	}
		

	public String readDoctor()  
	{   String output = ""; 
	 
	  try   { 
		  Connection con = connect(); 
		  
		   if (con == null)    
		   {return "Error while connecting to the database for reading."; } 
		 
		   // Prepare the html table to be displayed    
		   output = "<table border=\"1\"><tr><th>Doctor ID</th><th>Doctor Fname</th><th>Doctor Lname</th><th>"
		   		+ "Doctor Gender</th><th>Doctor Age</th><th>Doctor NIC</th><th>Doctor Email"
		   		+ "</th><th>Doctor Password</th><th>Doctor PhonerNumber"
		   		+"</th><th>Update</th>"
		   		+ "<th>Remove</th></tr>"; 
		 
		   String query = "select * from doctor";
		   
		   
		   Statement stmt = con.createStatement();    
		   ResultSet rs = stmt.executeQuery(query); 
		   
		   
		   // iterate through the rows in the result set    
		   while (rs.next())    
		   {   
			  
			   
			   String D_Id = Integer.toString(rs.getInt("docId"));     
			   String D_fname = rs.getString("fName");     
			   String D_lname = rs.getString("lNmae");     
			   String D_gender = rs.getString("gender");     
			   String D_age = Integer.toString(rs.getInt("age"));     
			   String D_docNIC = rs.getString("docNIC"); 
			   String D_docEmail = rs.getString("docEmail");
			   String D_password = rs.getString("passwod");
			   String D_phonenumber = Integer.toString(rs.getInt("phoneNumber"));
			  

		   
		 
		   
		    // Add into the html table
			    output += "<tr><td>" + D_Id + "</td>";   
		   		output += "<td>" + D_fname + "</td>";     
		   		output += "<td>" + D_lname + "</td>";    
		   		output += "<td>" + D_gender + "</td>";     
		   		output += "<td>" + D_age + "</td>"; 
		   		output += "<td>" + D_docNIC + "</td>";
		   		output += "<td>" + D_docEmail + "</td>";
		   		output += "<td>" + D_password + "</td>";
		   		output += "<td>" + D_phonenumber + "</td>";
		   		
		 
		    // buttons     
		   		output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"      
		   				+ "<td><form method=\"post\" action=\"Doctor.jsp\">"      
		   				+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"     "
		   				+ " class=\"btn btn-danger\">"      
		   				+ "<input name=\"docId\" type=\"hidden\" value=\"" + D_Id      
		   				+ "\">" + "</form></td></tr>";    
		   }
	  
	con.close();
		// Complete the html table
		output += "</table>";
	} catch (Exception e) {
	    output = "Error while reading the items.";
		System.err.println(e.getMessage());
	}
	return output;
}


	public String updateDoctor(String D_fname, String D_lname, String D_gender, int D_age, String D_docNIC,
			String D_docEmail, String D_password, int D_phonenumber) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			
			String query = "UPDATE doctor SET fName=?,lNmae=?, gender=?, age=?, docNIC=?, docEmail=?, passwod=? ,phoneNumber=?"
					+ " WHERE docId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, D_fname);
			preparedStmt.setString(2, D_lname);
			preparedStmt.setString(3, D_gender);
			preparedStmt.setInt(4, D_age);
			preparedStmt.setString(5, D_docNIC);
			preparedStmt.setString(6, D_docEmail);
			preparedStmt.setString(7, D_password);
			preparedStmt.setInt(8, D_phonenumber);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String deleteDoctor(String docId) {

		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from DoctorService" + "where docId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(docId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}



}
