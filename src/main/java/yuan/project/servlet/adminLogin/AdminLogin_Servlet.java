package yuan.project.servlet.adminLogin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yuan.project.dao.AdminLogin_Dao;
import yuan.project.entity.AdminLogin_Entity;
import yuan.project.service.login.AdminLogin_service;

/**
 * Servlet implementation class AdminLogin_Servlet
 */
public class AdminLogin_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*response.getWriter().append("Served at: ").append(request.getContextPath());*/
		HttpSession session = request.getSession();

	    session.setMaxInactiveInterval(604800);
	    request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
		String AdminName = request.getParameter("AdminName");
		String AdminPwd = request.getParameter("AdminPwd");
		AdminLogin_Entity admininformation = new AdminLogin_Entity();
		admininformation.setAdminName(AdminName);
		admininformation.setAdminPwd(AdminPwd);
		
		AdminLogin_service login=new AdminLogin_service();
		try {
			boolean bl = login.LoginAdmin(admininformation);
			if (bl) {
				//if login successful use this to change page
				/*String message = String.format(
						"Login successful!!Automatically jump to the dashboard page for you after 3 seconds!!"
								+ "<meta http-equiv='refresh' content='3;url=%s'/>",
						request.getContextPath() + "/homepage.html");
				request.setAttribute("message", message);*/
				AdminLogin_Dao ad= new AdminLogin_Dao();
				admininformation=ad.adminfind(AdminName);
				session.setAttribute("admininformation", admininformation);
				request.getRequestDispatcher("Management.html").forward(request, response);
			} else {
				//use this to redirect page
				request.setAttribute("error", "The account or password you entered is incorrect. Please re-enter!");
				request.getRequestDispatcher("login.html").forward(request, response);
			}
		} catch (Exception e) {
			//use this to redirect page
			e.printStackTrace();
			request.setAttribute("error", "The account or password you entered is incorrect. Please re-enter!");
			request.getRequestDispatcher("login.html").forward(request, response);
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
