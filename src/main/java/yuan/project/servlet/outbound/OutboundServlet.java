package yuan.project.servlet.outbound;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import yuan.project.entity.Inventory_Entity;
import yuan.project.service.inventory.InventoryService;
import yuan.project.service.outbound.Outbound_service;
import yuan.project.utils.JsonReader;

/**
 * Servlet implementation class OutboundServlet
 */
public class OutboundServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OutboundServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
						
				String selecttype=request.getParameter("selecttype");
				System.out.println(selecttype);
				 response.setContentType("text/html");
			     PrintWriter out = response.getWriter();
			     Outbound_service outboundservice= new Outbound_service();
				if("type".equals(selecttype)) {
					System.out.println("start to work with inbound check by type");
					String producttype=request.getParameter("producttype");
					JSONArray array= outboundservice.ItemOutboundCheckbytype(producttype);
					System.out.println("select result is :"+array);
					out.print(array);
					out.flush();
					out.close();			   
				}
					
				if("name".equals(selecttype)) {
					System.out.println("start to work with inbound check by name");
					String productname=request.getParameter("productname");
					JSONArray array= outboundservice.ItemOutboundCheckbytype(productname);
					out.print(array);
					out.flush();
					out.close();
				}
				if("all".equals(selecttype)) {
					System.out.println("start to work with inbound get all");
					JSONArray array= outboundservice.ItemInboundCheckAll();
					out.print(array);
					out.flush();
					out.close();	 			   
				}
				
				String type=" ";	
				if(request.getParameter("type").isEmpty()){	
					type= "do nothing";
			}else {
				 type=request.getParameter("type");
			}
		System.out.println(type);
		
		
		if(type.equals("sell")) {
			System.out.println("start to work with outbound sell check");
			response.setContentType("text/html");
			PrintWriter out1 = response.getWriter();
			Outbound_service os =new Outbound_service();
			JSONArray arraysell= os.Getsellinginformation();
			out1.print(arraysell);
			out1.flush();
			out1.close();	
		}else {		
			System.out.println("start to work with outbound aporior");
			response.setContentType("text/html");
			PrintWriter out2 = response.getWriter();
			Outbound_service os =new Outbound_service();
			JSONArray array= os.Aprior_algorithm();
			out2.print(array);
			out2.flush();
			out2.close();	
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		JsonReader jr= new JsonReader();
		
		String jsonbArray= jr.readJSONData(request);
		System.out.println(jsonbArray);
		JSONArray array = JSONArray.fromObject(jsonbArray);
		int x=array.size();
		System.out.println(x);
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		Date now = new Date(); 
		String dat= dateFormat.format(now);
		Calendar cal = Calendar.getInstance();	
		
		//generte invoiceNo
		String InvoiceNo = "A"+dat+cal.get(Calendar.HOUR)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND);
		System.out.println(" the identify number is "+InvoiceNo);
		String Msg = "error";
		for(int i =0; i<array.size();i++) {
		  System.out.println("di "+i+" result is "+array.get(i)); 
		  JSONObject Jobj= new JSONObject();
		  Jobj=(JSONObject) array.get(i); 
		  int product_id =Integer.parseInt(Jobj.getString("product_id"));
		  
		  String product_name = Jobj.getString("product_name");
		  
		  int outbound_quantity = Integer.parseInt(Jobj.getString("quantity"));
		  
		  double sumtotal = Double.valueOf(Jobj.getString("total"));		  
		  //update inventory information		  
		  InventoryService inventoryservice= new InventoryService();
		  JSONObject jb=inventoryservice.GetInventoryInfobyid(product_id);
		  int quantity = Integer.parseInt(jb.getString("PRODUCT_QUANTITY"));
		  System.out.println(outbound_quantity);
		  
		  
		 int new_quantity=quantity-outbound_quantity;
		  	
		  Inventory_Entity inventory = new Inventory_Entity();
		  inventory.setProduct_id(product_id);
		  inventory.setProduct_quantity(new_quantity);
		  System.out.println("the new quantity is " + inventory);
		  boolean index = inventoryservice.UpdateInventory(inventory, 2);
		  //update outbound information
		  if(index) {
			System.out.println("===============start add new outbound!");
		  Outbound_service outservice = new Outbound_service();
		  boolean index2=outservice.addnewoutbound(product_id,outbound_quantity,InvoiceNo);
		  	if(index2) {
		  		 Msg = "success";
		  		 System.out.println(Msg);
		  	}
		  }
		  
		  }
		PrintWriter out = response.getWriter();
		out.print(Msg);
		out.flush();
		out.close();	
		 
	}

}
