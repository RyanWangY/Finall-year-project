package yuan.project.service.lnbound;

import java.sql.Date;
/*import java.util.List;*/

import net.sf.json.JSONArray;
import yuan.project.dao.Inbound_Dao;
import yuan.project.entity.Inbound_Entity;
import yuan.project.entity.Inventory_Entity;
import yuan.project.service.inventory.InventoryService;
import yuan.project.service_interface.Inbound.Inbound_Interface;

public class InboundService implements Inbound_Interface{
	private Inbound_Dao inbounddao= new Inbound_Dao();

	public JSONArray ItemInboundCheckbytime(Date Inbound_timestart, Date Inbound_timeend) {
		// TODO Auto-generated method stub		
		return inbounddao.ItemInboundCheckbytime(Inbound_timestart, Inbound_timeend);
	}

	public JSONArray ItemInboundCheckbyname(String Item_name) {
		// TODO Auto-generated method stub
		return inbounddao.ItemInboundCheckbyname(Item_name);
	}
	
	public JSONArray ItemInboundCheckbytype(String type) {
		// TODO Auto-generated method stub
		return inbounddao.ItemInboundCheckbytype(type);
	}

	@Override
	public JSONArray ItemInboundCheckAll() {
		// TODO Auto-generated method stub
		return inbounddao.ItemInboundGetAll();
	}

	@Override
	public boolean ItemInbound(Inventory_Entity inventory, Inbound_Entity inbound) {
		// TODO Auto-generated method stub
		InventoryService is = new InventoryService();
		boolean index = false;
		int productid =is.checkItem(inventory.getProduct_name(),
				inventory.getProduct_suppler(),
				inventory.getBatch_number());
		
		if(productid == 0) {
			System.out.println("this is new product");
			
			is.AddInventory(inventory);
			productid = is.checkItem(inventory.getProduct_name(),
					inventory.getProduct_suppler(),
					inventory.getBatch_number());
			
			index = inbounddao.ItemInbound(productid,inbound);
			
		}else {	
			System.out.println("this product is exists");
				int product_quantity=is.getProductquantity(productid);
				
				product_quantity+=inventory.getProduct_quantity();
				
				System.out.println("new quantity is "+ product_quantity);
				
				Inventory_Entity new_quantity = new Inventory_Entity();
				
				new_quantity.setProduct_quantity(product_quantity);	
				new_quantity.setProduct_id(productid);
				is.UpdateInventory(new_quantity, 3);
				
			index = inbounddao.ItemInbound(productid,inbound);
		}
						
		return index ;
	}


}
