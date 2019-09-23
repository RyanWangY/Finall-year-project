package yuan.project.service.outbound;

import java.sql.Date;

import net.sf.json.JSONArray;
import yuan.project.dao.Outbound_Dao;
import yuan.project.entity.Outbound_Entity;
import yuan.project.service_interface.outbound.Outbound_Interface;

public class Outbound_service implements Outbound_Interface{

	private Outbound_Dao outdao=new Outbound_Dao();
	@Override
	public boolean ItemOutbound(Outbound_Entity Outbound) {
		// TODO Auto-generated method stub
		return outdao.ItemOutbound(Outbound);
	}

	@Override
	public JSONArray ItemOutboundCheckbytime(Date Outbound_timestart, Date Outbound_timeend) {
		// TODO Auto-generated method stub
		return outdao.ItemOutboundCheckbytime(Outbound_timestart, Outbound_timeend);
	}

	@Override
	public JSONArray ItemOutboundCheckbyname(String Item_name) {
		// TODO Auto-generated method stub
		return outdao.ItemOutboundCheckbyname(Item_name);
	}

	@Override
	public JSONArray ItemOutboundCheckbytype(String type) {
		// TODO Auto-generated method stub
		return outdao.ItemOutboundCheckbytype(type);
	}

	@Override
	public JSONArray Aprior_algorithm() {
		
		return outdao.getapriori();
	}

	@Override
	public JSONArray Getsellinginformation() {
		// TODO Auto-generated method stub
		return outdao.getselling();
	}

	public boolean addnewoutbound(int product_id, int outbound_quantity, String invoiceNo) {
		
		return outdao.addnewoutbound(product_id,outbound_quantity,invoiceNo);
		// TODO Auto-generated method stub
		
	}

	public JSONArray ItemInboundCheckAll() {
		// TODO Auto-generated method stub
		return outdao.ItemInboundCheckAll();
	}

}
