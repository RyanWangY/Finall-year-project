package yuan.project.dao_interface.Inventory;

import java.util.List;

import yuan.project.entity.Inventory_Entity;
import net.sf.json.JSONArray;

public interface InventoryDao_interface {

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
		
		int getproductquantity(int productid);
}
