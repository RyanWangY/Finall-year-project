package yuan.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import yuan.project.dao.algorithm.apriori.Apriori;
import yuan.project.dao.algorithm.apriori.Apriorinew;
import yuan.project.dao_interface.outbound.OutboundDao_Interface;
import yuan.project.entity.Outbound_Entity;
import yuan.project.utils.Dbmanage;
import yuan.project.utils.Gettime;
import yuan.project.utils.JsonFormat;

public class Outbound_Dao implements OutboundDao_Interface{

	/**
	    * 创建用于出库商品出库
	 * 
	 * */
	public boolean ItemOutbound(Outbound_Entity Outbound) {
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		System.out.println("finish link to database and start add items information");
		conn = dbmanage.initDB();
		Gettime g =new Gettime();
		try {
			String sql="INSERT INTO OUTBOUND "
					+ "(PRODUCT_ID,PRODUCT_NAME,PRODUCT_TYPE,PRODUCT_PRICE,PRODUCT_SUPPLER,PRODUCT_DESCRIPTION,PRODUCT_OUTBOUNDTIME,PRODUCT_QUANTITY) "
					+ "VALUES(null,?,?,?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, Outbound.getPRODUCT_NAME());
			psmt.setString(2, Outbound.getPRODUCT_TYPE());
			psmt.setFloat(3,Outbound.getPRODUCT_PRICE());
			psmt.setString(4,Outbound.getPRODUCT_SUPPLER());
			psmt.setString(5, Outbound.getPRODUCT_DESCRIPTION());
			psmt.setDate(6,Date.valueOf(g.gettime()));
			psmt.setInt(7, Outbound.getPRODUCT_QUANTITY());
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	/**
	    *创建用于出库商品查询
	 * 
	 * */
	public JSONArray ItemOutboundCheckbytime(Date Outbound_timestart, Date Outbound_timeend) {
		// TODO Auto-generated method stub
				//List<Outbound_Entity> OuboundInfo = new ArrayList<Outbound_Entity>();
				//Oubound_Entity OuboundInfo =null;;
				Dbmanage dbmanage = new Dbmanage();
				JsonFormat jf=new JsonFormat();
				 Connection conn = null;
				 JSONArray result =null;
				System.out.println("finish link to database and start track outboundInforamition by time");
				String sql="";
				conn = dbmanage.initDB();
				try {
					sql="select * from OUTBOUND where DATE_FORMAT(PRODUCT_OUTBOUNDTIME,'%Y%m%d')  between (?) and (?)";
					PreparedStatement psmt = conn.prepareStatement(sql);
					psmt.setDate(1, Outbound_timestart);
					psmt.setDate(2, Outbound_timeend);
					ResultSet rs = psmt.executeQuery();
					result=jf.resultSetToJsonArry(rs);
					return result;
						
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
	}
	
/**
 * 创建用于商品出库查询 keyword 商品名字
 * */
	public JSONArray ItemOutboundCheckbyname(String Item_name) {
		// TODO Auto-generated method stub
				//List<Outbound_Entity> outboundInfo = new ArrayList<Outbound_Entity>();
				//Outbound_Entity Outbound = null;;
				Dbmanage dbmanage = new Dbmanage();
				JsonFormat jf = new JsonFormat();
				JSONArray result = null;
				Connection conn = null;
				System.out.println("finish link to database and start track outboundInforamition by time");
				String sql = "";
				conn = dbmanage.initDB();
				try {
					sql="select * from outboundinformation where PRODUCT_NAME=?";
					PreparedStatement psmt = conn.prepareStatement(sql);
					psmt.setString(1, Item_name);
					ResultSet rs = psmt.executeQuery();
					result=jf.resultSetToJsonArry(rs);
					return result;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return result;
	}

	/**
	 * 创建用于商品出库查询 keyword 商品类型
	 * */
	
	public JSONArray ItemOutboundCheckbytype(String type) {
		//List<Outbound_Entity> outboundInfo = new ArrayList<Outbound_Entity>();
		//Outbound_Entity Outbound =null;;
		Dbmanage dbmanage = new Dbmanage();
		JsonFormat jf=new JsonFormat();
		JSONArray result =null;
		 Connection conn = null;
		System.out.println("finish link to database and start track outboundInforamition by time");
		String sql="";
		conn = dbmanage.initDB();
		try {
			sql="select * from outboundinformation where PRODUCT_TYPE=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, type);
			ResultSet rs = psmt.executeQuery();
			
			result=jf.resultSetToJsonArry(rs);
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 创建用于查询全部商品关联度
	 * @author Yuan
	 * @return JSONArray
	 * @description Used to query the degree of association between products
	 * */
	public JSONArray getapriori() {
		ArrayList<String> dataList = new ArrayList<String>();
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		System.out.println("finish link to database and start get products relationship information");
		conn = dbmanage.initDB();
		try {
			String sql1="";
			sql1=" select distinct InvoiceNo from OUTBOUND";
			PreparedStatement psmt = conn.prepareStatement(sql1);
			ResultSet rss = psmt.executeQuery();
			List<String> InvoiceNolist=new ArrayList<String>();
			while(rss.next()) {					
				InvoiceNolist.add(rss.getString("InvoiceNo"));
			}
			//Scan the database multiple times
			for(int i=0;i<InvoiceNolist.size();i++) {
				String InvoiceNo = InvoiceNolist.get(i);
				String sql=""; 
				sql="select PRODUCT_NAME from productset where InvoiceNo = ?";
				  PreparedStatement psmtt = conn.prepareStatement(sql); 
				  psmtt.setString(1,InvoiceNo);
				  
				  ResultSet rs = psmtt.executeQuery();
				  //String itemname=null; 
				  StringBuilder sb = new StringBuilder();
				  while(rs.next()) {
				  //itemname=rs.getString("PRODUCT_NAME"); 
				  //dataList.add(itemname); 
				  sb.append(rs.getString("PRODUCT_NAME")+",");
				  }
				  sb.append(",");
				  dataList.add(sb.toString());
				  //dataList.remove(dataList.get(dataList.size() - 2));
				  //System.out.println(sb);
			}						 			 
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Apriori apriori = new Apriori();
		Map<String, Integer> frequentCollectionMap = apriori.getFC(dataList);
		System.out.println("----------------频繁集Frequent set" + "----------------");
		Set<String> fcKeySet = frequentCollectionMap.keySet();
		for (String fcKey : fcKeySet) {
			System.out.println(fcKey + "  :  "
					+ frequentCollectionMap.get(fcKey));
		}
		Map<String, Double> relationRulesMap = apriori
				.getRelationRules(frequentCollectionMap);
		System.out.println("----------------关联规则Association rule " + "----------------");
		
		Set<String> rrKeySet = relationRulesMap.keySet();
		Map<String, String> map = new HashMap<String, String>();
		JSONArray array_test = new JSONArray();
		for (String rrKey : rrKeySet) {
			map.put("item_relation", rrKey);
			map.put("item_rule", relationRulesMap.get(rrKey).toString());
			array_test.add(map);
			System.out.println(rrKey + "  :  " + relationRulesMap.get(rrKey));
		}
			System.out.println(array_test);
		return array_test;	
	}

	public JSONArray getselling() {
		//ArrayList<String> dataList = new ArrayList<String>();
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		System.out.println("finish link to database and start get products relationship information");
		conn = dbmanage.initDB();
		JSONArray result =null;
		JsonFormat jf = new JsonFormat();
		try {
			String sql="select * from sellinformation";			
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			result=jf.resultSetToJsonArry(rs);
			System.out.println(result);
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean addnewoutbound(int product_id, int outbound_quantity, String invoiceNo) {
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		System.out.println("finish link to database and start add new outbound information");
		conn = dbmanage.initDB();
		Gettime g =new Gettime();
		try {
			String sql="INSERT INTO OUTBOUND "
					+ "(OUTBOUND_ID,PRODUCT_ID,InvoiceNo,outbound_quantity,PRODUCT_OUTBOUNDTIME) "
					+ "VALUES(null,?,?,?,?)";	
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, product_id);
			psmt.setString(2, invoiceNo);	
			psmt.setInt(3, outbound_quantity);
			psmt.setDate(4,Date.valueOf(g.gettime()));
			psmt.executeUpdate();
			
			//conn.commit();
			
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception

		}
		return false;
	}

	public JSONArray ItemInboundCheckAll() {
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		 JSONArray result =null;
		 JsonFormat jf=new JsonFormat();			
			try {
				conn = dbmanage.initDB();
				 System.out.println("finish link to database and start get all outboundinforamition");
				 String sql="select * from outboundinformation";
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
