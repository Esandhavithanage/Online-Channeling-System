package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class hospital {
	private Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/helthcare", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
public String addnewhospital(hospitalDeo hospitaldata) {
	
	String output = "";

	try {
		Connection con = connect();

		if (con == null) {
			return "Error while connecting to the database for readline";
		}

		String sql = "INSERT INTO `hospital`(`hospitalName`, `hospitalAddress`, `phone`, `hospitalEmail`, `password`) VALUES (?,?,?,?,?)";

		PreparedStatement statement = con.prepareStatement(sql);

		statement.setString(1,hospitaldata.getHospitalName());
		statement.setString(2, hospitaldata.getHospitalAddress());
		statement.setString(3, hospitaldata.getPhone());
		statement.setString(4, hospitaldata.getHospitalEmail());
		statement.setString(5, hospitaldata.getPassword());
		statement.execute();

		output = "Inserted successfully";

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
		return output;
	}

}
