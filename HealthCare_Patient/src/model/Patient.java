package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Patient {

	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/helthcare?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
			System.out.println("Connected Database");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	//---------------------------Insert--------------------

	public String AddPatient(String P_fname, String P_lname, String P_gender, int P_age, String P_patientNIC,
			String P_address, String P_email, String P_password , int P_phoneNo) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " INSERT INTO `patient`(`fNmae`, `lName`, `gender`, `age`, `patientNIC`, `address`, `patientEmail`, `passwod`,`phoneNumber`) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			// binding values
			preparedStmt.setString(1, P_fname);
			preparedStmt.setString(2, P_lname);
			preparedStmt.setString(3, P_gender);
			preparedStmt.setInt(4, P_age);
			preparedStmt.setString(5, P_patientNIC);
			preparedStmt.setString(6, P_address);
			preparedStmt.setString(7, P_email);
			preparedStmt.setString(8, P_password);
			preparedStmt.setInt(9, P_phoneNo);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the patient.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// ------------------------------Read----------------------------------

	public String readPatient()  
	{   String output = ""; 
	 
	  try   { 
		  Connection con = connect(); 
		  
		   if (con == null)    
		   {return "Error while connecting to the database for reading."; } 
		 
		   // Prepare the html table to be displayed    
		   output = "<table border=\"1\"><tr><th>Patient ID</th><th>Patient Fname</th><th>Patient Lname</th><th>"
		   		+ "Patient Gender</th><th>Patient Age</th><th>Patient NIC</th><th>Patient Address"
		   		+ "</th><th>Patient Email</th><th>Patient Password</th><th>Patient PhoneNo</th><th>Update</th>"
		   		+ "<th>Remove</th></tr>"; 
		 
		   String query = "select * from patient WHERE `status` = 'active';"; 
		   
		   Statement stmt = con.createStatement();    
		   ResultSet rs = stmt.executeQuery(query); 
		   
		   // iterate through the rows in the result set    
		   while (rs.next())    
		   {   
			   String P_Id = Integer.toString(rs.getInt("patientId"));     
			   String P_fname = rs.getString("fNmae");     
			   String P_lname = rs.getString("lName");     
			   String P_gender = rs.getString("gender");     
			   String P_age = Integer.toString(rs.getInt("age"));     
			   String P_patientNIC = rs.getString("patientNIC"); 
			   String P_address = rs.getString("address");
			   String P_email = rs.getString("patientEmail");
			   String P_password = rs.getString("passwod");
			   String P_phoneNo = Integer.toString(rs.getInt("phoneNumber"));
		   
		  
		   
		    // Add into the html table
			   	output += "<tr><td>" + P_Id + "</td>"; 
		   		output += "<td>" + P_fname + "</td>";     
		   		output += "<td>" + P_lname + "</td>";    
		   		output += "<td>" + P_gender + "</td>";     
		   		output += "<td>" + P_age + "</td>"; 
		   		output += "<td>" + P_patientNIC + "</td>";
		   		output += "<td>" + P_address + "</td>";
		   		output += "<td>" + P_email + "</td>";
		   		output += "<td>" + P_password + "</td>";
		   		output += "<td>" + P_phoneNo + "</td>";
		 
		    // buttons     
		   		output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"      
		   				+ "<td><form method=\"post\" action=\"Patient.jsp\">"      
		   				+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"     "
		   				+ " class=\"btn btn-danger\">"      
		   				+ "<input name=\"patientId\" type=\"hidden\" value=\"" + P_Id      
		   				+ "\">" + "</form></td></tr>";    
		   }
	  
	 // Connection con;
	con.close();
		// Complete the html table
		output += "</table>";
	} catch (Exception e) {
		output = "Error while reading the items.";
		System.err.println(e.getMessage());
	}
	return output;
}

	//--------------------------Update ---------------------

	public String updatePatient(String P_Id, String P_fname, String P_lname) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			
			String query = "UPDATE patient SET  fNmae =?, lName=? "
					+ "WHERE patientId=? AND `status` = 'active'";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, P_fname);
			preparedStmt.setString(2, P_lname);
			preparedStmt.setInt(3, Integer.parseInt(P_Id));


			
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

	// -------------------------------Delete-------------------------

	public String deletePatient(String patientId) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			
			String query = "UPDATE patient SET status = 'Deleted' "
					+ "WHERE patientId=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setInt(1, Integer.parseInt(patientId));


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
