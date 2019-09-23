package yuan.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.json.JSONArray;
import yuan.project.dao_interface.Inbound.InboundDao_Interface;
import yuan.project.entity.Inbound_Entity;
import yuan.project.utils.Dbmanage;
import yuan.project.utils.Gettime;
import yuan.project.utils.JsonFormat;

public class Inbound_Dao implements InboundDao_Interface{

	public boolean ItemInbound(int product_id,Inbound_Entity inbound) {
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		System.out.println("finish link to database and start add items information");
		conn = dbmanage.initDB();
		Gettime g =new Gettime();
		try {
			String sql="INSERT INTO INBOUND "
					+ "(INBOUND_ID,PRODUCT_ID,INBOUND_quantity,PRODUCT_INBOUNDTIME)"
					+ " VALUES(null,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, product_id);
			psmt.setInt(2, inbound.getInbound_quantity());
			psmt.setDate(3, Date.valueOf(g.gettime()));
			psmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public JSONArray ItemInboundCheckbytime(Date Inbound_timestart, Date Inbound_timeend) {
		// TODO Auto-generated method stub
		//List<Inbound_Entity> InboundInfo = new ArrayList<Inbound_Entity>();
		JsonFormat jf=new JsonFormat();
		//Inbound_Entity inbound =null;;
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		 JSONArray result =null;
		System.out.println("finish link to database and start track inboundinforamition by time");
		String sql="";
		conn = dbmanage.initDB();
		try {
			sql="select * from inboundinformation where DATE_FORMAT(PRODUCT_INBOUNDTIME,'%Y%m%d') between (?) and (?)";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setDate(1, Inbound_timestart);
			psmt.setDate(2, Inbound_timeend);
			ResultSet rs = psmt.executeQuery();
			result=jf.resultSetToJsonArry(rs);
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public JSONArray ItemInboundCheckbyname(String Item_name) {
		// TODO Auto-generated method stub
		//List<Inbound_Entity> InboundInfo = new ArrayList<Inbound_Entity>();
		//Inbound_Entity inbound =null;;
		JsonFormat jf=new JsonFormat();
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		 JSONArray result =null;
		System.out.println("finish link to database and start track inboundinforamition by time");
		String sql="";
		conn = dbmanage.initDB();
		try {
			sql="select * from inboundinformation where PRODUCT_NAME like " +"'%"+Item_name+"%'";
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			result=jf.resultSetToJsonArry(rs);
			return result;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public JSONArray ItemInboundCheckbytype(String type) {
		//List<Inbound_Entity> InboundInfo = new ArrayList<Inbound_Entity>();
		JsonFormat jf=new JsonFormat();
		//Inbound_Entity inbound =null;;
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		 JSONArray result =null;
		System.out.println("finish link to database and start track inboundinforamition by time");
		String sql="";
		conn = dbmanage.initDB();
		try {
			sql="select * from inboundinformation where PRODUCT_TYPE like "+"'%"+type+"%'";
			PreparedStatement psmt = conn.prepareStatement(sql);
			//psmt.setString(1, type);
			ResultSet rs = psmt.executeQuery();
			 result=jf.resultSetToJsonArry(rs);
				return result;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public JSONArray ItemInboundGetAll() {
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		 JSONArray result =null;
		 JsonFormat jf=new JsonFormat();			
			try {
				conn = dbmanage.initDB();
				 System.out.println("finish link to database and start get all inboundinforamition");
				 String sql="select * from inboundinformation";
				PreparedStatement psmt = conn.prepareStatement(sql);
				//psmt.setString(1, type);
				ResultSet rs = psmt.executeQuery();
				 result=jf.resultSetToJsonArry(rs);
				/*
				 * while(rs.next()) { Inbound_Entity inbound=new Inbound_Entity( rs.getInt(1),
				 * rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getString(5),
				 * rs.getString(6), rs.getDate(7), rs.getInt(8)); InboundInfo.add(inbound); }
				 */
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
	}

}
