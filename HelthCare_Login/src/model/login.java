package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login {

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
	
	public loginPOJO userLogin(loginPOJO loginPOJO,String type) {
		String output = "";
		loginPOJO pojo = new loginPOJO();

		try {
			Connection con = connect();

			if (con == null) {
				return pojo;
			}

			String queryhospital = "SELECT `hospitalId`, `hospitalName` FROM `hospital` WHERE `hospitalEmail` = ? AND `password` = ?";
			String querydoctor = "SELECT `docId`, `fName` FROM `doctor` WHERE `docEmail` = ? AND `passwod` = ?";
			String querypatient = "SELECT `patientId`, `fNmae` FROM `patient` WHERE  `patientEmail` = ? AND `passwod` = ?";
			
			if(type.equals("Admin")) {
				
				if(loginPOJO.getUname().equals("Admin@admin.com") && loginPOJO.getPassword().equals("admin")) {
					pojo.setType("Admin");
					pojo.setMesage("Login Sucsses");
					
				}else {
					pojo.setMesage("incorrect username or password");
				}
				
			}else if(type.equals("Doctor")) {
				PreparedStatement statement = con.prepareStatement(querydoctor);

				statement.setString(1,loginPOJO.uname);
				statement.setString(2,loginPOJO.password);
				ResultSet set = statement.executeQuery();

				if(set.next()) {
					pojo.setType(type);
					pojo.setMesage("Login Sucsses");
					pojo.setName(set.getString("fName"));
					pojo.setId(set.getInt("docId"));
				}else {
					pojo.setMesage("incorrect username or password");
				}
				
				
				
			}else if(type.equals("Patient")) {
				PreparedStatement statement = con.prepareStatement(querypatient);

				statement.setString(1,loginPOJO.uname);
				statement.setString(2,loginPOJO.password);
				ResultSet set = statement.executeQuery();

				if(set.next()) {
					pojo.setType(type);
					pojo.setMesage("Login Sucsses");
					pojo.setName(set.getString("fNmae"));
					pojo.setId(set.getInt("patientId"));
				}else {
					pojo.setMesage("incorrect username or password");
				}
				
				
				
			}else if(type.equals("Hospital")) {
				PreparedStatement statement = con.prepareStatement(queryhospital);

				statement.setString(1,loginPOJO.uname);
				statement.setString(2,loginPOJO.password);
				ResultSet set = statement.executeQuery();

				if(set.next()) {
					pojo.setType(type);
					pojo.setMesage("Login Sucsses");
					pojo.setName(set.getString("hospitalName"));
					pojo.setId(set.getInt("hospitalId"));
				}else {
					pojo.setMesage("incorrect username or password");
				}
				
			}
		
				
			   
				
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return pojo;
	}
}
