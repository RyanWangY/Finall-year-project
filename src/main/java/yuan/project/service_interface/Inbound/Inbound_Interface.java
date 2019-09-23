package yuan.project.service_interface.Inbound;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;
import yuan.project.entity.Inbound_Entity;
import yuan.project.entity.Inventory_Entity;

public interface Inbound_Interface {
	// use to store inbound information
	boolean ItemInbound(Inventory_Entity inventory,Inbound_Entity inbound);
	//use to display inbound information by time
	JSONArray ItemInboundCheckbytime(Date Inbound_timestart,Date Inbound_timeend);
	//use to display inbound information by name
	JSONArray ItemInboundCheckbyname(String Item_name);
	//use to display inbound information by type
	JSONArray ItemInboundCheckbytype(String type);
	
	JSONArray ItemInboundCheckAll();
		
}
