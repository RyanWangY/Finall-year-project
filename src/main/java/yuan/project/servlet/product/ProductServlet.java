package yuan.project.servlet.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import yuan.project.entity.Inventory_Entity;
import yuan.project.service.inventory.InventoryService;
import yuan.project.utils.JsonReader;

/**
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//String type=request.getParameter("type");
		InventoryService inventoryservice =new InventoryService();
		response.setContentType("text/html");   
		String pid=request.getParameter("pid");
		if(pid != null) {
			int productid= Integer.parseInt(pid);
			JSONObject jsonboject= inventoryservice.GetInventoryInfobyid(productid);
			System.out.println("select result is :"+ jsonboject);
			PrintWriter out = response.getWriter();
			out.print(jsonboject);
			out.flush();
			out.close();		   
		}
		else {
			String selecttype=request.getParameter("selecttype");
			System.out.println(selecttype);
		if("type".equals(selecttype)) {
			System.out.println("start to work with inbound check by type");
			String producttype=request.getParameter("producttype");
			JSONArray array= inventoryservice.GetInventoryInfo(producttype, 2);
			System.out.println("select type result is :"+ array);
			PrintWriter out = response.getWriter();
			out.print(array);
			out.flush();
			out.close();		   
		}
			
		if("name".equals(selecttype)) {
			System.out.println("start to work with inbound check by name");
			String productname=request.getParameter("productname");
			JSONArray array= inventoryservice.GetInventoryInfo(productname, 1);
			System.out.println("select name result is :"+ array);
			 PrintWriter out = response.getWriter();
			out.print(array);
			out.flush();
			out.close();
		}
		if("all".equals(selecttype)) {
			System.out.println("start to work with inbound check by name");
			JSONArray array= inventoryservice.GetInventoryInfo("all", 3);
			System.out.println(array);
			System.out.println("select all result is :"+ array);
			 PrintWriter out = response.getWriter();
			out.print(array);
			out.flush();
			out.close();
		}
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		JsonReader jr= new JsonReader();
		
		String jsonboject= jr.readJSONData(request);
		System.out.println(jsonboject);
		JSONObject fromObject = JSONObject.fromObject(jsonboject);
		
		String id =fromObject.getString("product_id");
		System.out.println(id);
		InventoryService inventoryservice =new InventoryService();
				

		int product_id = Integer.parseInt(fromObject.getString("product_id"));
		String product_name = fromObject.getString("product_name");
		String product_type = fromObject.getString("product_type");
		double product_price = Double.valueOf(fromObject.getString("product_price"));
		String product_suppler = fromObject.getString("product_suppler");
		Date productiondate = java.sql.Date.valueOf(fromObject.getString("Production_Date"));
		int product_shelflife = Integer.parseInt(fromObject.getString("Shelf_life"));
		String product_shelfunit = fromObject.getString("Shelflife_unit");
		int product_quantity = Integer.parseInt(fromObject.getString("product_quantity"));
		String product_status = fromObject.getString("product_status");
		System.out.println(
				product_id
				+"---"+product_name
				+"---"+product_type
				+"---"+product_price
				+"---"+product_suppler
				+"---"+productiondate
				+"---"+product_shelflife
				+"---"+product_shelfunit
				+"---"+product_quantity
				+"---"+product_status
				);
		Inventory_Entity INVE = new Inventory_Entity(product_id, 
				product_name, product_type, product_price, product_suppler, productiondate,
				product_shelflife, product_shelfunit, product_quantity, product_status);
		
		boolean index= inventoryservice.UpdateInventory(INVE, 1);
		if(index) {
			/*
			 * Map<String, String> map = new HashMap<String, String>(); map.put("Msg",
			 * "success"); JSONArray array= new JSONArray(); array.add(map);
			 */
			PrintWriter out = response.getWriter();
			//out.print(array);
			out.flush();
			out.close();	
		
		}	
	}
	

}
