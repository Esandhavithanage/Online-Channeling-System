package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Laboratory {
	//A common method to connect to the DB 
	private Connection connect() {
		Connection con = null;

		try

		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/helthcare", "root", "");
			System.out.println("Database Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// Add Lab Details
	
	public String AddLabDetails(String Lab_type, Date Lab_date,String Des,String PatientId,String HospitalId) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			String query = " insert into laboratoryreport (`labId`,`type`,`LabDate`,`Desacription`,`patientId`, `hospitalId`)" + " values (?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Lab_type);
			preparedStmt.setDate(3, Lab_date);
			preparedStmt.setString(4, Des);
			preparedStmt.setString(5, PatientId);
			preparedStmt.setString(6, HospitalId);

			preparedStmt.execute();
			con.close();

			output = "Inserted Lab Details successfully";
		} catch (Exception e) {
			output = "Error while inserting the Lab details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	// View Lab Details by Patient Id
	
	public String ViewLabDetailsByPatientID(int id) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border=\"1\"><tr><th>Lab Id</th> <th>Lab type</th><th>Lab date</th><th>Description</th><th>hospitalNmae</th></tr>";

			String query = "SELECT `labId`,`type`,`LabDate`,`Desacription`,`hospital`.`hospitalNmae` " + 
					"  FROM `laboratoryreport` INNER JOIN `hospital` " + 
					"    ON `laboratoryreport`.`hospitalId` = `hospital`.`hospitalId` " + 
					"     WHERE `laboratoryreport`.`patientId` = ? ";
			PreparedStatement statement = con.prepareStatement(query);

			statement.setInt(1,id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				String Lab_Id =Integer.toString(rs.getInt("labId"));
				String Lab_type = rs.getString("type");
				Date Lab_date = rs.getDate("LabDate");
				String Des = rs.getString("Desacription");
				String hospitalNmae =rs.getString("hospitalNmae");
			
				

				// add to html table
				output += "<tr><td>" + Lab_Id + "</td>";
				output += "<td>" + Lab_type + "</td>";
				output += "<td>" + Lab_date + "</td>";
				output += "<td>" + Des + "</td>";
				output += "<td>" + hospitalNmae + "</td>";
			
				

				// buttons
				output += "</tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Lab details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// View Lab Details by Hospital Id
	
	public String ViewLabDetailsByHospitalID(int id) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			output = "<table border=\"1\"><tr><th>Lab Id</th> <th>Lab type</th><th>Lab date</th><th>Description</th><th>patientNmae</th></tr>";

			String query = "SELECT `labId`,`type`,`LabDate`,`Desacription`,`patient`.`fNmae` as patientname " + 
					"  FROM `laboratoryreport` INNER JOIN `patient` " + 
					"    ON `laboratoryreport`.`patientId`= `patient`.`patientId` " + 
					"     WHERE `laboratoryreport`.`hospitalId` = ?";
			
			PreparedStatement statement = con.prepareStatement(query);

			statement.setInt(1,id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				String Lab_Id =Integer.toString(rs.getInt("labId"));
				String Lab_type = rs.getString("type");
				Date Lab_date = rs.getDate("LabDate");
				String Des = rs.getString("Desacription");
				String patientNmae =rs.getString("patientname");
			
				

				// add to html table
				output += "<tr><td>" + Lab_Id + "</td>";
				output += "<td>" + Lab_type + "</td>";
				output += "<td>" + Lab_date + "</td>";
				output += "<td>" + Des + "</td>";
				output += "<td>" + patientNmae + "</td>";
			
				

				// buttons
				output += "</tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Lab details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	
	
	
	// update payment

	public String Updatelab(String Lab_Id, String Lab_type, String Lab_date,String Des,String PatientId,String HospitalId) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE laboratoryreport SET type=?,LabDate=?, Desacription=?, patientId =?, hospitalId =? WHERE labId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			
			preparedStmt.setString(1,Lab_type);
			preparedStmt.setString(2, Lab_date);
			preparedStmt.setString(3,Des);
			preparedStmt.setString(4,PatientId);
			preparedStmt.setString(5,HospitalId);
			preparedStmt.setInt(6, Integer.parseInt(Lab_Id));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated Lab Details successfully";
		} catch (Exception e) {
			output = "Error while updating the Lab details.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	// Delete payment details
	
	public String RemoveLab(String Lab_Id)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for removing."; } 
	 
			String query = "delete from laboratoryreport where labId=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values   
			preparedStmt.setInt(1, Integer.parseInt(Lab_Id)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			output = "Deleted successfully";   
			}   
			catch (Exception e)   
			{    
				output = "Error while deleting the Lab details.";    
				System.err.println(e.getMessage());   } 
	 
	  return output;  }
	
	

}
