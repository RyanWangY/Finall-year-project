package yuan.project.entity;

import java.sql.Date;

public class Inventory_Entity {
	private int product_id;
	private String product_name;
	private String product_type;
	private double product_price;
	private String product_suppler;
	/*private String PRODUCT_DESCRIPTION;*/
	private Date productiondate;
	private int product_shelflife;
	private String product_shelfunit;
	private int batch_number;
	private Date product_inboundtime;
	private Date product_outboundtime;
	private int product_quantity;
	private String product_status;

	
	public int getProduct_id() {
		return product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
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


	public double getProduct_price() {
		return product_price;
	}


	public void setProduct_price(double product_price) {
		this.product_price = product_price;
	}


	public String getProduct_suppler() {
		return product_suppler;
	}


	public void setProduct_suppler(String product_suppler) {
		this.product_suppler = product_suppler;
	}


	public Date getProductiondate() {
		return productiondate;
	}


	public void setProductiondate(Date productiondate) {
		this.productiondate = productiondate;
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


	public int getBatch_number() {
		return batch_number;
	}


	public void setBatch_number(int batch_number) {
		this.batch_number = batch_number;
	}


	public Date getProduct_inboundtime() {
		return product_inboundtime;
	}


	public void setProduct_inboundtime(Date product_inboundtime) {
		this.product_inboundtime = product_inboundtime;
	}


	public Date getProduct_outboundtime() {
		return product_outboundtime;
	}


	public void setProduct_outboundtime(Date product_outboundtime) {
		this.product_outboundtime = product_outboundtime;
	}


	public int getProduct_quantity() {
		return product_quantity;
	}


	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}


	public String getProduct_status() {
		return product_status;
	}


	public void setProduct_status(String product_status) {
		this.product_status = product_status;
	}


	public Inventory_Entity(String product_name, String product_type, double product_price, String product_suppler,
			Date productiondate, int product_shelflife, String product_shelfunit, int batch_number,
			Date product_inboundtime, Date product_outboundtime, int product_quantity, String product_status) {
		super();
		this.product_name = product_name;
		this.product_type = product_type;
		this.product_price = product_price;
		this.product_suppler = product_suppler;
		this.productiondate = productiondate;
		this.product_shelflife = product_shelflife;
		this.product_shelfunit = product_shelfunit;
		this.batch_number = batch_number;
		this.product_inboundtime = product_inboundtime;
		this.product_outboundtime = product_outboundtime;
		this.product_quantity = product_quantity;
		this.product_status = product_status;
	}


	public Inventory_Entity(int product_id, String product_name, String product_type, double product_price,
			String product_suppler, Date productiondate, int product_shelflife, String product_shelfunit,
			int product_quantity, String product_status) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_type = product_type;
		this.product_price = product_price;
		this.product_suppler = product_suppler;
		this.productiondate = productiondate;
		this.product_shelflife = product_shelflife;
		this.product_shelfunit = product_shelfunit;
		this.product_quantity = product_quantity;
		this.product_status = product_status;
	}
	
	

	public Inventory_Entity(String product_name, String product_type, double product_price, String product_suppler,
			Date productiondate, int product_shelflife, String product_shelfunit, int batch_number,
			int product_quantity, String product_status) {
		super();
		this.product_name = product_name;
		this.product_type = product_type;
		this.product_price = product_price;
		this.product_suppler = product_suppler;
		this.productiondate = productiondate;
		this.product_shelflife = product_shelflife;
		this.product_shelfunit = product_shelfunit;
		this.batch_number = batch_number;
		this.product_quantity = product_quantity;
		this.product_status = product_status;
	}


	/*	public Inventory_Entity(int pRODUCT_ID, String pRODUCT_NAME, String pRODUCT_TYPE, float pRODUCT_PRICE,
			String pRODUCT_SUPPLER, String pRODUCT_DESCRIPTION, Date pRODUCT_INBOUNDTIME, Date pRODUCT_OUTBOUNDTIME,
			int pRODUCT_QUANTITY, String pRODUCT_STATUS) {
		super();
		PRODUCT_ID = pRODUCT_ID;
		PRODUCT_NAME = pRODUCT_NAME;
		PRODUCT_TYPE = pRODUCT_TYPE;
		PRODUCT_PRICE = pRODUCT_PRICE;
		PRODUCT_SUPPLER = pRODUCT_SUPPLER;
		PRODUCT_DESCRIPTION = pRODUCT_DESCRIPTION;
		PRODUCT_INBOUNDTIME = pRODUCT_INBOUNDTIME;
		PRODUCT_OUTBOUNDTIME = pRODUCT_OUTBOUNDTIME;
		PRODUCT_QUANTITY = pRODUCT_QUANTITY;
		PRODUCT_STATUS = pRODUCT_STATUS;
	}*/
	public Inventory_Entity() {
		// TODO Auto-generated constructor stub
	}

	
	
}
