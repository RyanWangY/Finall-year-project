package yuan.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import yuan.project.dao_interface.Inventory.InventoryDao_interface;
import yuan.project.entity.Inventory_Entity;
import yuan.project.utils.Dbmanage;
import yuan.project.utils.Gettime;
import yuan.project.utils.JsonFormat;

public class Inventory_Dao implements InventoryDao_interface{

	public int checkItem(String item_name,String suppler,int batch_number) {
		// TODO Auto-generated method stub
		Dbmanage dbmanage = new Dbmanage();
		//Inventory_Entity inventory= new Inventory_Entity();
		 Connection conn = null;
		System.out.println("finish link to database and start check item");
		conn = dbmanage.initDB();
		int itemId = 0;
		try {
			String sql="SELECT * FROM PRODUCT WHERE PRODUCT_NAME = ? "
					+ "and PRODUCT_SUPPLER = ? and BATCH_NUMBER = ?";
			PreparedStatement psmt = conn
					.prepareStatement(sql);
			psmt.setString(1, item_name);
			psmt.setString(2, suppler);
			psmt.setInt(3, batch_number);
			ResultSet rs= psmt.executeQuery();
			while (rs.next()) {
				 itemId =rs.getInt("PRODUCT_ID");
				//inventory.setProduct_id(itemId);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return itemId;
	}

	public boolean UpdateInventory(Inventory_Entity inventory,int type) {
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		System.out.println("finish link to database and start update items to inventory");
		conn = dbmanage.initDB();
		if(type==1) {
			try {
				
				String sql=
						"UPDATE PRODUCT set"
						+ " PRODUCT_NAME = ?, "
						+ "PRODUCT_TYPE = ?, "
						+ "PRODUCT_PRICE = ?, "
						+ "PRODUCT_SUPPLER = ?, "
						+ "PRODUCT_SHELFLIFE = ?, "
						+ "PRODUCT_SHELFUNIT = ?, "
						+ "PRODUCT_PRODUCTIONDATE = ?, "
						+ "PRODUCT_QUANTITY = ?, "
						+ "PRODUCT_STATUS = ? "
						+ "where PRODUCT_ID = ?; ";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, inventory.getProduct_name());
				psmt.setString(2, inventory.getProduct_type());	
				psmt.setDouble(3, inventory.getProduct_price());
				psmt.setString(4, inventory.getProduct_suppler());
				psmt.setInt(5,inventory.getProduct_shelflife());
				psmt.setString(6, inventory.getProduct_shelfunit());
				psmt.setDate(7, inventory.getProductiondate());
				psmt.setInt(8,inventory.getProduct_quantity());
				psmt.setString(9, inventory.getProduct_status());				
				psmt.setInt(10,inventory.getProduct_id());
				psmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(type==2){
			try {
				String sql="UPDATE PRODUCT set PRODUCT_QUANTITY=? where PRODUCT_ID=? ";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, inventory.getProduct_quantity());
				psmt.setInt(2,inventory.getProduct_id());
				psmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(type==3) {
			System.out.println("start to update new quantity!");
			try {
				String sql="UPDATE PRODUCT set PRODUCT_QUANTITY = ? where PRODUCT_ID=? ";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setDouble(1, inventory.getProduct_quantity());
				psmt.setInt(2,inventory.getProduct_id());
				psmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
		
	}

	public JSONArray GetInventoryInfo(String parameter,int type) {
		Dbmanage dbmanage = new Dbmanage();
		//Connection conn = null;
		JsonFormat jf=new JsonFormat();
		JSONArray result =null;
		try {
			Connection conn = dbmanage.initDB();
			System.out.println("finish link to database and start check items in inventory");
			String sql="select * from product where 1=1";
			StringBuilder strb = new StringBuilder(sql);		
			if(type==1) 
			{
				strb.append(" and PRODUCT_NAME like " +"'%"+parameter+"%'");
				String statement=strb.toString();
				System.out.println("sql stament is :"+statement);
				PreparedStatement psmt = conn.prepareStatement(statement);
				//psmt.setString(1, parameter);
				ResultSet rs = psmt.executeQuery();
				result=jf.resultSetToJsonArry(rs);
			}
			if(type==2)
			{
				strb.append(" and PRODUCT_TYPE like " +"'%"+parameter+"%'");
				String statement=strb.toString();
				System.out.println("sql stament is :"+statement);
				PreparedStatement psmt = conn.prepareStatement(statement);
				//psmt.setString(1, parameter);
				ResultSet rs = psmt.executeQuery();
				result=jf.resultSetToJsonArry(rs);
			}
			if(type==3)
			{
				String statement=strb.toString();
				System.out.println("sql stament is :"+statement);
				PreparedStatement psmt = conn.prepareStatement(statement);
				ResultSet rs = psmt.executeQuery();
				result=jf.resultSetToJsonArry(rs);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see yuan.project.dao_interface.Inventory.InventoryDao_interface
	 * #AddInventory(yuan.project.entity.Inventory_Entity)
	 */
	
	public boolean AddInventory(Inventory_Entity inventory) {
		Dbmanage dbmanage = new Dbmanage();
		 Connection conn = null;
		System.out.println("finish link to database and start add items to inventory");
		conn = dbmanage.initDB();
		//Gettime g =new Gettime();
		try {
			//when add new item let outboundtime = null
			String sql="INSERT INTO PRODUCT "
					+ "(PRODUCT_ID,BATCH_NUMBER,PRODUCT_NAME,PRODUCT_TYPE,PRODUCT_PRICE,"
					+ "PRODUCT_SUPPLER,PRODUCT_SHELFLIFE,PRODUCT_SHELFUNIT,PRODUCT_PRODUCTIONDATE,"
					+ "PRODUCT_QUANTITY,PRODUCT_STATUS)"
					+ "VALUES(null,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, inventory.getBatch_number());
			psmt.setString(2, inventory.getProduct_name());
			psmt.setString(3, inventory.getProduct_type());
			psmt.setDouble(4, inventory.getProduct_price());
			psmt.setString(5, inventory.getProduct_suppler());
			psmt.setInt(6, inventory.getProduct_shelflife());
			psmt.setString(7, inventory.getProduct_shelfunit());
			psmt.setDate(8,inventory.getProductiondate());
			psmt.setInt(9, inventory.getProduct_quantity());
			psmt.setString(10, inventory.getProduct_status());
			psmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public JSONObject GetInventoryInfo(int productid) {
		Dbmanage dbmanage = new Dbmanage();
		//Connection conn = null;
		//JsonFormat jf=new JsonFormat();
		JSONObject result =null;
		
		try {
			Connection conn = dbmanage.initDB();
			System.out.println("finish link to database and start check items in inventory by id");
			String sql="select * from PRODUCT where PRODUCT_ID = ?";							
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, productid);
				ResultSet rs = psmt.executeQuery();
				result=JsonFormat.resultSetToJsonObject(rs)	;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public JSONArray GetshortageInfo(int quantity) {
		Dbmanage dbmanage = new Dbmanage();
		JsonFormat jf=new JsonFormat();
		JSONArray result =null;
		try {
			Connection conn = dbmanage.initDB();
			System.out.println("finish link to database and start check items in inventory");
			String sql="select * from shortageinfo where 1=1";
			StringBuilder strb = new StringBuilder(sql);		
				strb.append(" and PRODUCT_QUANTITY <= "+quantity+";");
				String statement=strb.toString();
				System.out.println("sql stament is :"+statement);
				PreparedStatement psmt = conn.prepareStatement(statement);
				//psmt.setString(1, parameter);
				ResultSet rs = psmt.executeQuery();
				result=jf.resultSetToJsonArry(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				return result;
	}

	@Override
	public JSONArray GetExpiredInfo(int days) {
		Dbmanage dbmanage = new Dbmanage();
		JSONArray array_test = new JSONArray();
		try {
			
			Connection conn = dbmanage.initDB();
			System.out.println("finish link to database and start check Expired product");	
			System.out.println("step 1:start to check product id");
			String sql = "select PRODUCT_ID from PRODUCT";
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rss = psmt.executeQuery();
			List<String> product_idlist=new ArrayList<String>();
			while(rss.next()) {					
				product_idlist.add(rss.getString("PRODUCT_ID"));
			}
			System.out.println(product_idlist);
			JSONArray result =new JSONArray();
			System.out.println("step 2:start to check product");
			for(int i=0;i<product_idlist.size();i++) {
				int product_id = Integer.parseInt(product_idlist.get(i));
				CallableStatement callableStatement = conn.prepareCall("{call get_Expiredinfo(?,?)}");
				callableStatement.setInt(1, product_id);
				callableStatement.setInt(2, days);
				ResultSet rs = callableStatement.executeQuery();
				JSONObject jsonboject=JsonFormat.resultSetToJsonObject(rs);
				//JSONArray array_test = new JSONArray();
				if(jsonboject.isEmpty()){
					
				}else
					result.add(jsonboject);
			}
			array_test=result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
			System.out.println(array_test);
				return array_test;
	}

	public int getproductquantity(int productid) {
		Dbmanage dbmanage = new Dbmanage();
		//Inventory_Entity inventory= new Inventory_Entity();
		 Connection conn = null;
		System.out.println("finish link to database and start get product quantity");
		conn = dbmanage.initDB();
		int quantity = 0;
		try {
			String sql="SELECT PRODUCT_QUANTITY FROM PRODUCT WHERE PRODUCT_ID = ?";
			PreparedStatement psmt = conn
					.prepareStatement(sql);
			psmt.setInt(1, productid);
			ResultSet rs= psmt.executeQuery();
			while (rs.next()) {
				quantity =rs.getInt("PRODUCT_QUANTITY");
				System.out.println(quantity);
				//inventory.setProduct_id(itemId);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return quantity;
	}

}
