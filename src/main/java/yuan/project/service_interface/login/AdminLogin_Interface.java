package yuan.project.service_interface.login;

import yuan.project.entity.AdminLogin_Entity;

public interface AdminLogin_Interface {
	// Use for supply register service
	boolean RegisterAdmin(AdminLogin_Entity Admin);
	
	// Use for supply Login service
	boolean LoginAdmin(AdminLogin_Entity Admin);
}
