package yuan.project.entity;

import java.sql.Date;

public class Inbound_Entity {
	private int inbound_id;
	//private int PRODUCT_ID;
	private int batch_number;
	private String product_name;
	private String product_type;
	//private float PRODUCT_PRICE;
	private String product_suppler;
	private int product_shelflife;
	private String product_shelfunit; 
	private int inbound_quantity;
	private Date product_productdate;
	private Date product_inboundtime;
	
	public Inbound_Entity() {
		super();
	}

	public int getInbound_id() {
		return inbound_id;
	}

	public void setInbound_id(int inbound_id) {
		this.inbound_id = inbound_id;
	}

	public int getBatch_number() {
		return batch_number;
	}

	public void setBatch_number(int batch_number) {
		this.batch_number = batch_number;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getProduct_suppler() {
		return product_suppler;
	}

	public void setProduct_suppler(String product_suppler) {
		this.product_suppler = product_suppler;
	}

	public int getProduct_shelflife() {
		return product_shelflife;
	}

	public void setProduct_shelflife(int product_shelflife) {
		this.product_shelflife = product_shelflife;
	}

	public String getProduct_shelfunit() {
		return product_shelfunit;
	}

	public void setProduct_shelfunit(String product_shelfunit) {
		this.product_shelfunit = product_shelfunit;
	}

	public int getInbound_quantity() {
		return inbound_quantity;
	}

	public void setInbound_quantity(int inbound_quantity) {
		this.inbound_quantity = inbound_quantity;
	}

	public Date getProduct_productdate() {
		return product_productdate;
	}

	public void setProduct_productdate(Date product_productdate) {
		this.product_productdate = product_productdate;
	}

	public Date getProduct_inboundtime() {
		return product_inboundtime;
	}

	public void setProduct_inboundtime(Date product_inboundtime) {
		this.product_inboundtime = product_inboundtime;
	}

	public Inbound_Entity(int batch_number, String product_name, String product_type, String product_suppler,
			int product_shelflife, String product_shelfunit, int inbound_quantity, Date product_productdate) {
		super();
		this.batch_number = batch_number;
		this.product_name = product_name;
		this.product_type = product_type;
		this.product_suppler = product_suppler;
		this.product_shelflife = product_shelflife;
		this.product_shelfunit = product_shelfunit;
		this.inbound_quantity = inbound_quantity;
		this.product_productdate = product_productdate;
	}
	
	
	
}
