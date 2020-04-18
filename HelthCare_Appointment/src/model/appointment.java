package model;

import java.awt.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class appointment {
	
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

	public String addNewAppointment(appointmentPOJO appointmentData) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for readline";
			}

			String sql = "INSERT INTO `appoinment`(`appoinmentDate`,`patientId`, `paymentId`, `docId`, `hospitalId`) VALUES (?,?,?,?,?)";

			PreparedStatement statement = con.prepareStatement(sql);

			statement.setDate(1,java.sql.Date.valueOf(appointmentData.getAppointmentDate()));
			statement.setInt(2, appointmentData.getPatientId());
			statement.setInt(3, appointmentData.getPaymentId());
			statement.setInt(4, appointmentData.getDocId());
			statement.setInt(5, appointmentData.getHospitalId());
			statement.execute();

			output = "Inserted successfully";
			con.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return output;
	}
	
	public ArrayList<appointmentPOJO> getApointmentBydocAndDate(int DocID,String date) {
		String output = "";
		ArrayList<appointmentPOJO> resultSet = new ArrayList<appointmentPOJO>();

		try {
			Connection con = connect();

			if (con == null) {
				return resultSet;
			}

			String query = "SELECT a.appoinmentId,a.appoinmentDate,p.fNmae,h.hospitalName,py.amount,a.docId,a.hospitalId,a.patientId,a.paymentId " + 
					"FROM appoinment a " + 
					"INNER JOIN payment py " + 
					"ON a.paymentId = py.paymentId " + 
					"INNER JOIN patient p " + 
					"ON a.patientId = p.patientId " + 
					"INNER JOIN hospital h " + 
					"ON a.hospitalId = h.hospitalId WHERE a.docId=? AND a.appoinmentDate=?";
			PreparedStatement statement = con.prepareStatement(query);

			statement.setInt(1,DocID);
			statement.setDate(2,java.sql.Date.valueOf(date));
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				appointmentPOJO appointmentPOJO = new appointmentPOJO();
				appointmentPOJO.setAppointmentID(set.getInt("appoinmentId"));
				appointmentPOJO.setHospitalName(set.getString("hospitalName"));
				appointmentPOJO.setPatientName(set.getString("fNmae"));
				appointmentPOJO.setDocId(set.getInt("docId"));
				appointmentPOJO.setHospitalId(set.getInt("hospitalId"));
				appointmentPOJO.setPatientId(set.getInt("patientId"));
				appointmentPOJO.setPaymentId(set.getInt("paymentId"));
				appointmentPOJO.setPayment(set.getDouble("amount"));
				appointmentPOJO.setAppointmentDate(String.valueOf(set.getDate("appoinmentDate")));
				resultSet.add(appointmentPOJO);
				System.out.println(appointmentPOJO.getAppointmentDate());
				System.out.println(appointmentPOJO.getAppointmentID());
			   
				
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return resultSet;
	}

	public ArrayList<appointmentPOJO> getApointmentBypatientAndDate(int pid, String date) {
		String output = "";
		ArrayList<appointmentPOJO> resultSet = new ArrayList<appointmentPOJO>();

		try {
			Connection con = connect();

			if (con == null) {
				return resultSet;
			}

			String query = "SELECT a.appoinmentId,a.appoinmentDate,d.fName,h.hospitalName,py.amount,a.docId,a.hospitalId,a.patientId,a.paymentId " + 
					"FROM appoinment a " + 
					"INNER JOIN payment py " + 
					"ON a.paymentId = py.paymentId " + 
					"INNER JOIN doctor d " + 
					"ON a.docId = d.docId " + 
					"INNER JOIN hospital h " + 
					"ON a.hospitalId = h.hospitalId "
					+"WHERE a.patientId = ? AND a.appoinmentDate =?";
				
			PreparedStatement statement = con.prepareStatement(query);

			statement.setInt(1,pid);
			statement.setDate(2,java.sql.Date.valueOf(date));
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				appointmentPOJO appointmentPOJO = new appointmentPOJO();
				appointmentPOJO.setAppointmentID(set.getInt("appoinmentId"));
				appointmentPOJO.setDocName(set.getString("fName"));
				appointmentPOJO.setHospitalName(set.getString("hospitalName"));
				appointmentPOJO.setPayment(set.getDouble("amount"));
				appointmentPOJO.setDocId(set.getInt("docId"));
				appointmentPOJO.setHospitalId(set.getInt("hospitalId"));
				appointmentPOJO.setPatientId(set.getInt("patientId"));
				appointmentPOJO.setPaymentId(set.getInt("paymentId"));
				appointmentPOJO.setAppointmentDate(String.valueOf(set.getDate("appoinmentDate")));
				resultSet.add(appointmentPOJO);
				System.out.println(appointmentPOJO.getAppointmentDate());
				System.out.println(appointmentPOJO.getAppointmentID());
			   
				
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return resultSet;
	}

	public ArrayList<appointmentPOJO> getApointmentByhospitalAndDate(int hid, String date) {
		String output = "";
		ArrayList<appointmentPOJO> resultSet = new ArrayList<appointmentPOJO>();

		try {
			Connection con = connect();

			if (con == null) {
				return resultSet;
			}

			String query = "SELECT a.appoinmentId,a.appoinmentDate,p.fNmae,d.fName as dname ,py.amount,a.docId,a.hospitalId,a.patientId,a.paymentId " + 
					"FROM appoinment a " + 
					"INNER JOIN payment py " + 
					"ON a.paymentId = py.paymentId " + 
					"INNER JOIN doctor d "+ 
					"ON a.docId = d.docId " + 
					"INNER JOIN patient p " + 
					"ON a.patientId = p.patientId "+"WHERE a.hospitalId =? AND a.appoinmentDate =?";
			PreparedStatement statement = con.prepareStatement(query);

			statement.setInt(1,hid);
			statement.setDate(2,java.sql.Date.valueOf(date));
			ResultSet set = statement.executeQuery();

			while (set.next()) {
				appointmentPOJO appointmentPOJO = new appointmentPOJO();
				appointmentPOJO.setAppointmentID(set.getInt("appoinmentId"));
				appointmentPOJO.setPatientName(set.getString("fNmae"));
				appointmentPOJO.setDocName(set.getString("dname"));
				appointmentPOJO.setPayment(set.getDouble("amount"));
				appointmentPOJO.setDocId(set.getInt("docId"));
				appointmentPOJO.setHospitalId(set.getInt("hospitalId"));
				appointmentPOJO.setPatientId(set.getInt("patientId"));
				appointmentPOJO.setPaymentId(set.getInt("paymentId"));
				appointmentPOJO.setAppointmentDate(String.valueOf(set.getDate("appoinmentDate")));
				resultSet.add(appointmentPOJO);
				System.out.println(appointmentPOJO.getAppointmentDate());
				System.out.println(appointmentPOJO.getAppointmentID());
			   
				
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return resultSet;
	}
	
	public String updateappoitment(appointmentPOJO appointmentData) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for readline";
			}

			String sql = "UPDATE `appoinment` SET `appoinmentDate`=?,`docId`=? WHERE `appoinmentId`=?";

			PreparedStatement statement = con.prepareStatement(sql);

			statement.setDate(1,java.sql.Date.valueOf(appointmentData.getAppointmentDate()));
			statement.setInt(2, appointmentData.getDocId());
			statement.setInt(3, appointmentData.getAppointmentID());
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

	public String deleteappoitment(appointmentPOJO appointmentData) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for readline";
			}

			String sql = "DELETE FROM `appoinment` WHERE `appoinmentId` = ?";

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, appointmentData.getAppointmentID());
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
