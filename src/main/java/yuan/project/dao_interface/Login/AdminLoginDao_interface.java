package yuan.project.dao_interface.Login;

import yuan.project.entity.AdminLogin_Entity;

public interface AdminLoginDao_interface {
	/*
	 * Find admin based on adminname and password
	 * 
	 * */
		Boolean adminfind(String adminname ,String adminpwd);
		
		/*
		 * register admin 
		 * 
		 * */	
		
		boolean register(AdminLogin_Entity admin);
		/*
		 * Find admin based on adminName
		 * 
		 * */	
		AdminLogin_Entity adminfind(String adminName);
}
