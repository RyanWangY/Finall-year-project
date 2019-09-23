package yuan.project.dao;

import yuan.project.dao_interface.Login.AdminLoginDao_interface;
import yuan.project.entity.AdminLogin_Entity;
import yuan.project.utils.Dbmanage;
import yuan.project.utils.JsonFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.google.gson.Gson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
//import java.sql.Statement;
/**
 * @author YuanWang
 * */
public class AdminLogin_Dao implements AdminLoginDao_interface{
//Confirm if the account exists
	public Boolean adminfind(String adminname, String adminpwd) {
		 Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		System.out.println("finish link to database and start seraching");
		try {
			conn = dbmanage.initDB();
			// String sql = "SELECT * FROM DATABASE WHERE admin_username="+AdminName+";";
			//check account with password
			PreparedStatement psmt = conn
					.prepareStatement("SELECT * FROM ADMINISTRATORS WHERE admin_useraccount=(?) and admin_pasword=(?)");
			psmt.setString(1, adminname);
			psmt.setString(2, adminpwd);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				return true;
			}
			//for check email address with password
			PreparedStatement psmtl = conn
					.prepareStatement("SELECT * FROM ADMINISTRATORS WHERE email_address=(?) and admin_pasword=(?)");
			psmtl.setString(1, adminname);
			psmtl.setString(2, adminpwd);
			ResultSet rsl = psmtl.executeQuery();
			while (rsl.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			dbmanage.fecharBDcon(conn);
		}
		return false;
	}
	
	// register a admin account
	public boolean register(AdminLogin_Entity admin) {
		// TODO Auto-generated method stub
		 Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		 //Statement sta = null;
		try {
			conn = dbmanage.initDB();
			PreparedStatement psmt = conn.prepareStatement(
					"INSERT INTO ADMINISTRATORS (admin_firstName,admin_lastName,admin_useraccount,email_address,admin_password) VALUES(?,?,?,?,?)");
			psmt.setString(1, admin.getFirstName());
			psmt.setString(2, admin.getLastName());
			psmt.setString(3, admin.getAdminName());
			psmt.setString(4, admin.getAdminPwd());
			psmt.executeUpdate();
			/*
			 * String sql = "INSERT INTO ADMINISTRATORS VALUES(" + admin.getFirstName() +
			 * ',' + admin.getAdminName() + ',' + admin.getAdminPwd() + ',' +
			 * admin.getLastName() + ")"; sta.executeQuery(sql);
			 */
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {

			dbmanage.fecharBDcon(conn);
		}
	}

	public AdminLogin_Entity adminfind(String adminName) {
		// TODO Auto-generated method stub
		AdminLogin_Entity admin = new AdminLogin_Entity();
		Dbmanage dbmanage = new Dbmanage();
		Connection conn = null;
		// Statement sta = null;
		//String NewadminName = null;
		conn = dbmanage.initDB();
		try {
			// String sql = "SELECT * FROM DATABASE WHERE admin_username="+AdminName+";";
			PreparedStatement psmt = conn.prepareStatement("SELECT * FROM ADMINISTRATORS WHERE admin_useraccount= (?) or email_address=(?)");
			psmt.setString(1, adminName);
			psmt.setString(2, adminName);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				String CheckAdmin = rs.getString("admin_useraccount");
				String Checkemail=rs.getString("email_address");
				admin.setAdminName(CheckAdmin);
				System.out.println("name " + CheckAdmin + " is in the data base");
				return admin;
			}
			/*PreparedStatement psmtL = conn.prepareStatement("SELECT * FROM ADMINISTRATORS WHERE email_address=(?)");
			psmt.setString(1, adminName);
			ResultSet rsL = psmtL.executeQuery();
			while (rsL.next()) {
				String CheckAdmin = rs.getString("email_address");
				admin.setAdminName(CheckAdmin);
				System.out.println("name " + CheckAdmin + " is in the data base");
				return admin;
			}*/
		} catch (Exception e) {
			// TODO: handle exception
			// System.out.println("name:"+adminName+"is not in the data base");
		} finally {
			dbmanage.fecharBDcon(conn);
		}
		System.out.println("name:" + adminName + " is not in the data base");
		return admin;
	}
	
	public JSONObject getinformationbyjson() {
		JsonFormat jf=new JsonFormat();
		 Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		 JSONObject reslut =null;
		System.out.println("finish link to database and start seraching");
		try {
			conn = dbmanage.initDB();
			// String sql = "SELECT * FROM DATABASE WHERE admin_username="+AdminName+";";
			//check account with password
			PreparedStatement psmt = conn
					.prepareStatement("SELECT * FROM ADMINISTRATORS");
			ResultSet rs = psmt.executeQuery();
				 reslut=jf.resultSetToJsonObject(rs);
				return reslut;
			
			}catch (Exception e) {
				// TODO: handle exception
				// System.out.println("name:"+adminName+"is not in the data base");
			}
		return reslut;
	}
	public JSONArray getinformation() {
		JsonFormat jf=new JsonFormat();
		 Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		 JSONArray reslut =null;
		// Gson gson=new Gson();
		System.out.println("finish link to database and start seraching");
		try {
			conn = dbmanage.initDB();
			// String sql = "SELECT * FROM DATABASE WHERE admin_username="+AdminName+";";
			//check account with password
			PreparedStatement psmt = conn
					.prepareStatement("SELECT * FROM ADMINISTRATORS");
			ResultSet rs = psmt.executeQuery();
			reslut=jf.resultSetToJsonArry(rs);
				return reslut;
			
			}catch (Exception e) {
				// TODO: handle exception
				// System.out.println("name:"+adminName+"is not in the data base");
			}
		return reslut;
	}
	@SuppressWarnings("rawtypes")
	public List getlistinformation() {
		JsonFormat jf=new JsonFormat();
		 Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		 JSONArray reslut =null;
		// Gson gson=new Gson();
		System.out.println("finish link to database and start seraching");
		try {
			conn = dbmanage.initDB();
			// String sql = "SELECT * FROM DATABASE WHERE admin_username="+AdminName+";";
			//check account with password
			PreparedStatement psmt = conn
					.prepareStatement("SELECT * FROM ADMINISTRATORS");
			ResultSet rs = psmt.executeQuery();
			return JsonFormat.RsToJson(rs);
				//return reslut;
			
			}catch (Exception e) {
				// TODO: handle exception
				// System.out.println("name:"+adminName+"is not in the data base");
			}
		return null;
	}

}
