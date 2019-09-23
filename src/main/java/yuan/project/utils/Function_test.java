package yuan.project.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import yuan.project.dao.AdminLogin_Dao;
import yuan.project.dao.algorithm.apriori.Apriori;
import yuan.project.dao.algorithm.apriori.Apriorinew;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

public class Function_test {

	/*
	 * public static void main(String[] args) { // TODO Auto-generated method stub
	 * AdminLogin_Dao ad =new AdminLogin_Dao(); JSONObject
	 * result=ad.getinformationbyjson(); System.out.println(result);
	 * 
	 * JSONArray result1 = ad.getinformation(); System.out.println(result1);
	 * 
	 * @SuppressWarnings("rawtypes") List ll=ad.getlistinformation();
	 * System.out.println(ll); }
	 */

	public static void main (String[] args) throws Exception{
		
		/*
		 * int a = 10; a+=10; System.out.println(a);
		 */
		int a = 10;
		a-=5;
		System.out.println(a);
		
	 /* {	
		ArrayList<String> dataList = new ArrayList<String>();
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		System.out.println("finish link to database and start add items information");
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
				  String itemname; 
				  StringBuilder sb = new StringBuilder();
				  while(rs.next()) {
					  itemname=rs.getString("PRODUCT_NAME"); 
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
		System.out.println("----------------频繁集" + "----------------");
		Set<String> fcKeySet = frequentCollectionMap.keySet();
		for (String fcKey : fcKeySet) {
			System.out.println(fcKey + "  :  "
					+ frequentCollectionMap.get(fcKey));
		}
		Map<String, Double> relationRulesMap = apriori
				.getRelationRules(frequentCollectionMap);
		System.out.println("----------------关联规则" + "----------------");
		Set<String> rrKeySet = relationRulesMap.keySet();
		//StringBuilder sb = new StringBuilder();
		Map<String, String> map = new HashMap<String, String>();
		JSONArray array_test = new JSONArray();
		for (String rrKey : rrKeySet) {
			map.put("item_relation", rrKey);
			map.put("item_rule", relationRulesMap.get(rrKey).toString());
			array_test.add(map);
			 System.out.println(rrKey + "  :  " + relationRulesMap.get(rrKey)); 
		}
			System.out.println(array_test);	  
		}*/
	
	
	//JsonFormat jf=new JsonFormat();
	/*
	 * Map<String, String> map = new HashMap<String, String>();
	 * 
	 * while(rs.next()) { map.put("PRODUCT_NAME", rs.getString(1));
	 * map.put("PRODUCT_PRODUCTIONDATE", rs.getDate(2).toString());
	 * map.put("Expired_time", rs.getDate(3).toString()); map.put("Remaining_date",
	 * rs.getInt(4)+""); array_test.add(map); }
	 * 
	 * result =array_test;
	 */
		/*
		 * Dbmanage dbmanage = new Dbmanage(); JSONArray array_test = new JSONArray();
		 * try { System.out.
		 * println("finish link to database and start check items in inventory");
		 * Connection conn = dbmanage.initDB(); String sql =
		 * "select PRODUCT_ID from PRODUCT"; PreparedStatement psmt =
		 * conn.prepareStatement(sql); ResultSet rss = psmt.executeQuery(); List<String>
		 * product_idlist=new ArrayList<String>(); while(rss.next()) {
		 * product_idlist.add(rss.getString("PRODUCT_ID")); }
		 * System.out.println(product_idlist); JSONArray result =new JSONArray();
		 * for(int i=0;i<product_idlist.size();i++) { int product_id =
		 * Integer.parseInt(product_idlist.get(i)); CallableStatement callableStatement
		 * = conn.prepareCall("{call get_Expiredinfo(?,?)}");
		 * callableStatement.setInt(1, product_id); callableStatement.setInt(2, 400);
		 * ResultSet rs = callableStatement.executeQuery(); JSONObject
		 * jsonboject=JsonFormat.resultSetToJsonObject(rs); //JSONArray array_test = new
		 * JSONArray(); result.add(jsonboject);
		 * 
		 * } array_test=result; } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * System.out.println(array_test);
		 */
	
	/*public static void main(String[] args) {
		
		 Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		System.out.println("finish link to database and start seraching");
		try {
			conn = dbmanage.initDB();
			// String sql = "SELECT * FROM DATABASE WHERE admin_username="+AdminName+";";
			//check account with password
			StringBuilder sb = new StringBuilder();
			PreparedStatement psmt = conn
					.prepareStatement("SELECT * FROM ADMINISTRATORS");
			//psmt.setString(1, adminname);
			//psmt.setString(2, adminpwd);
			ResultSet rs = psmt.executeQuery();
			Map<String, String> map = new HashMap<String, String>();
			JSONArray array_test = new JSONArray();
			while (rs.next()) {
				map.put("jsonObject", rs.getString(2));
				map.put("jsonObject1", rs.getString(3));
				map.put("jsonObject2", rs.getString(4));
				array_test.add(map);
			}
			
			//map.put("2", "efg");
			
			
			JSONObject jsonObject = JSONObject.fromObject(map);

			System.out.println(array_test);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			dbmanage.fecharBDcon(conn);
		}
	}*/
	
	}
}
