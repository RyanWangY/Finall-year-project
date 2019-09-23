package yuan.project.service.login;

import yuan.project.dao.AdminLogin_Dao;
import yuan.project.entity.AdminLogin_Entity;
import yuan.project.service_interface.login.AdminLogin_Interface;

public class AdminLogin_service implements AdminLogin_Interface{

	public boolean RegisterAdmin(AdminLogin_Entity Admin) {
		String pwd=Admin.getAdminPwd();
		String vtypwd=Admin.getvtyAdminPwd();
		String AdminName=Admin.getAdminName();
		if(!pwd.equals(vtypwd)) {
			System.err.println("Inconsistent entry password!");
			  //throw new Exception("Inconsistent entry password!");
			  return false;
		}
		AdminLogin_Dao adi=new AdminLogin_Dao();
		//confirm adminname is exits
		AdminLogin_Entity arv= adi.adminfind(AdminName);
		String adminame=arv.getAdminName();
		if(adminame == null) {
			boolean bl=adi.register(Admin);
			return bl;
		}else {			
			System.err.println("Admin ID already exists");
			//throw new Exception("Admin ID already exists");
			return false;
		}
	}

	public boolean LoginAdmin(AdminLogin_Entity Admin) {
		String adminname=Admin.getAdminName();		
		String pwd=Admin.getAdminPwd();
		AdminLogin_Dao adi=new AdminLogin_Dao();
		AdminLogin_Entity arv= adi.adminfind(adminname);
		String adaccount=arv.getAdminName();
		if(adaccount!=null) {
			boolean bl=adi.adminfind(adminname, pwd);
			if(bl) {	
				
				return true;
			}else {
				//throw new Exception("");		
				System.err.println("Admin password is not correct!");
				return false;
			}
		}else {		
			//throw new Exception("Admin ID is not exists");
			System.err.println("Admin account is not exists");
			return false;
		}
		
		
	}

}
