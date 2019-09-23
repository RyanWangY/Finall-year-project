package yuan.project.service_interface.Inventory;

import java.util.List;

import net.sf.json.JSONArray;
import yuan.project.entity.Inventory_Entity;

public interface InventoryService_interface {
	
	// check the item is in database or not
	int checkItem(String item_name,String suppler,int batch_number);
	// when inbound/outbound finished use this to update inventory
	boolean UpdateInventory(Inventory_Entity inventory,int type);
	//
	boolean AddInventory(Inventory_Entity inventory);
	//get Inventory information
	JSONArray GetInventoryInfo(String parameter,int type);
	
	JSONArray GetshortageInfo(int quantity);
	
	JSONArray GetExpiredInfo(int days);
}
