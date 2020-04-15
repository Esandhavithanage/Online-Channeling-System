package model;

import java.sql.*;
import java.sql.SQLException;

public class appointment {
	private Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	public String name(Date appoinmentDateTime, int patientId, int paymentId, int docId, int hospitalId) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for readline";
			}

			String sql = "INSERT INTO `appoinment`(`appoinmentDateTime`, `patientId`, `paymentId`, `docId`, `hospitalId`) VALUES (?,?,?,?,?)";

			PreparedStatement statement = con.prepareStatement(sql);

			statement.setDate(1, appoinmentDateTime);
			statement.setInt(2, patientId);
			statement.setInt(3, paymentId);
			statement.setInt(4, docId);
			statement.setInt(5, hospitalId);
			statement.execute();

			output = "Inserted successfully";

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return output;
	}

	public String name() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for readline";
			}

			String query = "select * from items";
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {
				
			}
			
		} catch (Exception e) {

		}

		return output;
	}
}
