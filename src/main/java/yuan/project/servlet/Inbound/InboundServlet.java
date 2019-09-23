package yuan.project.servlet.Inbound;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import yuan.project.entity.Inbound_Entity;
import yuan.project.entity.Inventory_Entity;
import yuan.project.service.inventory.InventoryService;
import yuan.project.service.lnbound.InboundService;
import yuan.project.utils.JsonReader;

/**
 * Servlet implementation class InboundServlet
 */
public class InboundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InboundServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		InboundService Inboundservice= new InboundService();
		String type=request.getParameter("type");
		System.out.println("start to work with inbound check");
		if("add".equals(type)) {
			// CREATE NEW ITEM
			/*
			 * String PRODUCT_NAME=request.getParameter("PRODUCT_NAME"); String
			 * PRODUCT_TYPE=request.getParameter("PRODUCT_TYPE"); double PRODUCT_PRICE
			 * =Double.valueOf(request.getParameter("PRODUCT_PRICE")); String
			 * PRODUCT_SUPPLER=request.getParameter("PRODUCT_SUPPLER"); int
			 * PRODUCT_SHELFLIFE =
			 * Integer.parseInt(request.getParameter("PRODUCT_SHELFLIFE")); String
			 * PRODUCT_SHELFUNIT=request.getParameter("PRODUCT_SHELFUNIT"); int
			 * PRODUCT_QUANTITY=Integer.parseInt(request.getParameter("PRODUCT_QUANTITY"));
			 * String PRODUCT_STATUS =request.getParameter("PRODUCT_STATUS");
			 */
		}

			String selecttype=request.getParameter("selecttype");
			System.out.println(selecttype);
			 response.setContentType("text/html");
		     PrintWriter out = response.getWriter();
			if("type".equals(selecttype)) {
				System.out.println("start to work with inbound check by type");
				String producttype=request.getParameter("producttype");
				JSONArray array= Inboundservice.ItemInboundCheckbytype(producttype);
				System.out.println("select result is :"+array);
				out.print(array);
				out.flush();
				out.close();			   
			}
				
			if("name".equals(selecttype)) {
				System.out.println("start to work with inbound check by name");
				String productname=request.getParameter("productname");
				JSONArray array= Inboundservice.ItemInboundCheckbyname(productname);
				out.print(array);
				out.flush();
				out.close();
			}
			if("date".equals(selecttype)) {
				System.out.println("start to work with inbound check by time");
				String dateStrstart=request.getParameter("startdate");
				String dateStrend=request.getParameter("enddate");
				Date datestart=java.sql.Date.valueOf(dateStrstart);
				Date dateend=java.sql.Date.valueOf(dateStrend);
				JSONArray array= Inboundservice.ItemInboundCheckbytime(datestart, dateend);
				out.print(array);
				out.flush();
				out.close();	 			   
			}
			if("all".equals(selecttype)) {
				System.out.println("start to work with inbound get all");
				JSONArray array= Inboundservice.ItemInboundCheckAll();
				out.print(array);
				out.flush();
				out.close();	 			   
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
		
		//int product_id = Integer.parseInt(fromObject.getString("product_id"));
		String product_name = fromObject.getString("product_name");
		int batch_number = Integer.parseInt(fromObject.getString("batch_number"));
		String product_type = fromObject.getString("product_type");
		double product_price = Double.valueOf(fromObject.getString("product_price"));
		String product_suppler = fromObject.getString("product_suppler");
		Date production_date = java.sql.Date.valueOf(fromObject.getString("Production_Date"));
		int product_shelflife = Integer.parseInt(fromObject.getString("Shelf_life"));
		String product_shelfunit = fromObject.getString("Shelflife_unit");
		int inbound_quantity = Integer.parseInt(fromObject.getString("product_quantity"));
		String product_status = fromObject.getString("product_status");
		
		InboundService inboundservice = new InboundService();
		//InventoryService inventoryservice = new InventoryService();
		
		Inventory_Entity inventory = new Inventory_Entity
				(product_name, product_type, 
						product_price, 
						product_suppler, 
						production_date, 
						product_shelflife, 
						product_shelfunit, 
						batch_number, 
						inbound_quantity, 
						product_status);
		
		
		Inbound_Entity inbound = new Inbound_Entity();
		
		inbound.setInbound_quantity(inbound_quantity);
		
		boolean index = inboundservice.ItemInbound(inventory,inbound);
		if(index) {
				JSONArray array = new JSONArray();
				array.add(fromObject);
			PrintWriter out = response.getWriter();
			out.print(array);
			out.flush();
			out.close();	
		}
		

	}

}
