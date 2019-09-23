package yuan.project.service.inventory;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import yuan.project.dao.Inventory_Dao;
import yuan.project.entity.Inventory_Entity;
import yuan.project.service_interface.Inventory.InventoryService_interface;

public class InventoryService implements InventoryService_interface{

		private Inventory_Dao inventorydao= new Inventory_Dao();
	public int checkItem(String item_name,String suppler,int batch_number) {
		// TODO Auto-generated method stub
		return inventorydao.checkItem(item_name, suppler,batch_number);
	}


	public boolean UpdateInventory(Inventory_Entity inventory, int type) {
		// TODO Auto-generated method stub
		return inventorydao.UpdateInventory(inventory, type);
	}

	public boolean AddInventory(Inventory_Entity inventory) {
		// TODO Auto-generated method stub
		return inventorydao.AddInventory(inventory);
	}

	public JSONArray GetInventoryInfo(String parameter,int type) {
		// TODO Auto-generated method stub
		return inventorydao.GetInventoryInfo(parameter, type);
	}
	public JSONObject GetInventoryInfobyid(int productid) {
		// TODO Auto-generated method stub
		return inventorydao.GetInventoryInfo(productid);
	}


	@Override
	public JSONArray GetshortageInfo(int quantity) {
		// TODO Auto-generated method stub
		return inventorydao.GetshortageInfo(quantity);
	}


	@Override
	public JSONArray GetExpiredInfo(int days) {
		// TODO Auto-generated method stub
		return inventorydao.GetExpiredInfo(days);
	}


	public int getProductquantity(int productid) {
		// TODO Auto-generated method stub
		return inventorydao.getproductquantity(productid);
	}


}
