package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

public ArrayList<hospitalDeo> getHospitalDetails() {
	
	ArrayList<hospitalDeo> resultSet = new ArrayList<hospitalDeo>();

	try {
		Connection con = connect();

		if (con == null) {
			return resultSet;
		}

		String query = "SELECT `hospitalId`, `hospitalName`, `hospitalAddress`, `phone`, `hospitalEmail`, `password` FROM `hospital`";
		PreparedStatement statement = con.prepareStatement(query);
		ResultSet set = statement.executeQuery();

		while (set.next()) {
			hospitalDeo deo = new hospitalDeo();
			deo.setHospitalId(set.getInt("hospitalIId"));
			deo.setHospitalName(set.getString("hospitalName"));
			deo.setHospitalAddress(set.getString("hospitalAddress"));
			deo.setPhone(set.getString("phone"));
			deo.setHospitalEmail(set.getString("hospitalEmail"));
			deo.setPassword(set.getString("password"));
			resultSet.add(deo);   
			
		}
		con.close();
	} catch (Exception e) {
		System.out.println(e);
	}
	
	return resultSet;
	}

public String updateHospitalDetails(hospitalDeo hospitalDeo) {
	String output = "";

	try {
		Connection con = connect();

		if (con == null) {
			return "Error while connecting to the database for readline";
		}

		String sql = "UPDATE `hospital` SET `hospitalName`= ?,`hospitalAddress`= ?,`phone`= ?,`hospitalEmail`= ?,`password`= ? WHERE `hospitalId` = ?";

		PreparedStatement statement = con.prepareStatement(sql);

		statement.setString(1,hospitalDeo.getHospitalName());
		statement.setString(2, hospitalDeo.getHospitalAddress());
		statement.setString(3,hospitalDeo.getPhone());
		statement.setString(4,hospitalDeo.getHospitalEmail());
		statement.setString(5,hospitalDeo.getPassword());
		statement.setInt(6,hospitalDeo.getHospitalId());
		statement.execute();

		output = "Updated successfully";
		con.close();

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}

	return output;
	}

public String deletehospital(hospitalDeo hospitalDeo) {
	String output = "";

	try {
		Connection con = connect();

		if (con == null) {
			return "Error while connecting to the database for readline";
		}

		String sql = "DELETE FROM `hospital` WHERE `hospitalId` = ?";

		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, hospitalDeo.getHospitalId());
		statement.execute();

		output = "Deleted successfully";
		con.close();

	} catch (SQLException e) {
		System.out.println(e.getMessage());
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}

	return output;
}
}
