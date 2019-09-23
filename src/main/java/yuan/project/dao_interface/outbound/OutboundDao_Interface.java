package yuan.project.dao_interface.outbound;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;
import yuan.project.entity.Outbound_Entity;

public interface OutboundDao_Interface {
	
	boolean ItemOutbound(Outbound_Entity Outbound);
	//boolean ItemInventory(Outbound_Entity Outbound);
	JSONArray ItemOutboundCheckbytime(Date Outbound_timestart, Date Outbound_timeend);
	//use to display Outbound information by name
	JSONArray ItemOutboundCheckbyname(String Item_name);
	//use to display Outbound information by type
	JSONArray ItemOutboundCheckbytype(String type);
	//
	JSONArray getapriori();
}
