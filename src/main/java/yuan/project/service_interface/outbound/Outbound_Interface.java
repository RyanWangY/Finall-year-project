package yuan.project.service_interface.outbound;

import java.sql.Date;
import java.util.List;
import net.sf.json.JSONArray;
import yuan.project.entity.Outbound_Entity;

public interface Outbound_Interface {
	
	// use to store Outbound information
	boolean ItemOutbound(Outbound_Entity Outbound);
	//use to display Outbound information by time
	JSONArray ItemOutboundCheckbytime(Date Outbound_timestart,Date Outbound_timeend);
	//use to display Outbound information by name
	JSONArray ItemOutboundCheckbyname(String Item_name);
	//use to display Outbound information by type
	JSONArray ItemOutboundCheckbytype(String type);
	
	JSONArray Aprior_algorithm();
	
	
	JSONArray Getsellinginformation();
}
