package yuan.project.servlet.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import yuan.project.service.inventory.InventoryService;

/**
 * Servlet implementation class StockInfoServlet
 */
public class StockInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		InventoryService inventoryservice =new InventoryService();
		String index= request.getParameter("index");
		System.out.println(index);
		//response.setContentType("text/html");   
		if(index.equals("stock")) {
		System.out.println("start to work with stock check");
		//set value for chack stock
		int quantity= 100;
		JSONArray array=inventoryservice.GetshortageInfo(quantity);
		System.out.println("check stock result is :"+ array);
		PrintWriter out = response.getWriter();
		out.print(array);
		out.flush();
		out.close();	
		}
		
		if(index.equals("Expired")) {
		int days=400;
		JSONArray arrayday=inventoryservice.GetExpiredInfo(days);
		System.out.println("check expired result is :"+ arrayday);
		PrintWriter out = response.getWriter();
		out.print(arrayday);
		out.flush();
		out.close();	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
