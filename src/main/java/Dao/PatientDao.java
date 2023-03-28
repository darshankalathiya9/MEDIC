package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.DBConnection;
import Model.Patient;

public class PatientDao {
	public static void insertPatient(Patient p) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "insert into patient (FirstName, Lastname, Gender, Address, Mobile, Email, Password) values (?,?,?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setString(1, p.getFisrtName());
			pst.setString(2, p.getLastName());
			pst.setString(3, p.getGender());
			pst.setString(4, p.getAddress());
			pst.setLong(5, p.getMobile());
			pst.setString(6, p.getEmail());
			pst.setString(7, p.getPassword());
			
			pst.executeUpdate();
			System.out.println("Data Inserted Succesfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Patient loginPatient(Patient p) {
		Patient p1 = null;
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "select * from patient where Email = ? and Password = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, p.getEmail());
			pst.setString(2, p.getPassword());
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				p1 = new Patient();
				p1.setID(rs.getInt("ID"));
				p1.setFisrtName(rs.getNString("FirstName"));
				p1.setLastName(rs.getString("LastName"));
				p1.setGender(rs.getString("Gender"));
				p1.setAddress(rs.getString("Address"));
				p1.setMobile(rs.getLong("Mobile"));
				p1.setEmail(rs.getString("Email"));
				p1.setPassword(rs.getString("Password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p1;
	}
	
	public static void updateProfile(Patient p) {
		try {
			Connection conn = DBConnection.createConnection();
			String sql = "update patient set FirstName=?, LastName=?, Gender=?, Address=?, Mobile=?, Email = ? where ID= ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setString(1, p.getFisrtName());
			pst.setString(2, p.getLastName());
			pst.setString(3, p.getGender());
			pst.setString(4, p.getAddress());
			pst.setLong(5, p.getMobile());
			pst.setString(6, p.getEmail());
			pst.setInt(7, p.getID());
			
			pst.executeUpdate();
			System.out.println("Data Updated Succesfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
