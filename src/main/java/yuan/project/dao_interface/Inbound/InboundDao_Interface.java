package yuan.project.dao_interface.Inbound;

import java.sql.Date;

import net.sf.json.JSONArray;
import yuan.project.entity.Inbound_Entity;
import yuan.project.entity.Inventory_Entity;

public interface InboundDao_Interface {
	
	boolean ItemInbound(int product_id ,Inbound_Entity inbound);
	//boolean ItemInventory(Inbound_Entity inbound);
	JSONArray ItemInboundCheckbytime(Date Inbound_timestart, Date Inbound_timeend);
	//use to display inbound information by name
	JSONArray ItemInboundCheckbyname(String Item_name);
		//use to display inbound information by type
	JSONArray ItemInboundCheckbytype(String type);
	
	
	JSONArray ItemInboundGetAll();
}
