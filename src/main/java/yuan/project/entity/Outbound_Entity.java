package yuan.project.entity;

import java.sql.Date;

public class Outbound_Entity {
	private int PRODUCT_ID;
	//private String name;
	private String PRODUCT_NAME;
	private String PRODUCT_TYPE;
	private float PRODUCT_PRICE;
	private String PRODUCT_SUPPLER;
	private String PRODUCT_DESCRIPTION;
	private Date PRODUCT_OUTBOUNDTIME;
	private int PRODUCT_QUANTITY;
	
	public int getPRODUCT_ID() {
		return PRODUCT_ID;
	}
	public void setPRODUCT_ID(int PRODUCT_ID) {
		this.PRODUCT_ID = PRODUCT_ID;
	}
	/*public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}*/
	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}
	public void setPRODUCT_NAME(String PRODUCT_NAME) {
		this.PRODUCT_NAME = PRODUCT_NAME;
	}
	public String getPRODUCT_TYPE() {
		return PRODUCT_TYPE;
	}
	public void setPRODUCT_TYPE(String PRODUCT_TYPE) {
		this.PRODUCT_TYPE = PRODUCT_TYPE;
	}
	public float getPRODUCT_PRICE() {
		return PRODUCT_PRICE;
	}
	public void setPRODUCT_PRICE(float PRODUCT_PRICE) {
		this.PRODUCT_PRICE = PRODUCT_PRICE;
	}
	public int getPRODUCT_QUANTITY() {
		return PRODUCT_QUANTITY;
	}
	public void setPRODUCT_QUANTITY(int PRODUCT_QUANTITY) {
		this.PRODUCT_QUANTITY = PRODUCT_QUANTITY;
	}
	
	public String getPRODUCT_SUPPLER() {
		return PRODUCT_SUPPLER;
	}
	public void setPRODUCT_SUPPLER(String pRODUCT_SUPPLER) {
		PRODUCT_SUPPLER = pRODUCT_SUPPLER;
	}
	public String getPRODUCT_DESCRIPTION() {
		return PRODUCT_DESCRIPTION;
	}
	public void setPRODUCT_DESCRIPTION(String PRODUCT_DESCRIPTION) {
		this.PRODUCT_DESCRIPTION = PRODUCT_DESCRIPTION;
	}
	public Date getPRODUCT_OUTBOUNDTIME() {
		return PRODUCT_OUTBOUNDTIME;
	}
	public void setPRODUCT_OUTBOUNDTIME(Date PRODUCT_OUTBOUNDTIME) {
		this.PRODUCT_OUTBOUNDTIME = PRODUCT_OUTBOUNDTIME;
	}
	public Outbound_Entity(int PRODUCT_ID, String PRODUCT_NAME, String PRODUCT_TYPE, float PRODUCT_PRICE, int PRODUCT_QUANTITY,
			String PRODUCT_DESCRIPTION, Date PRODUCT_OUTBOUNDTIME) {
		super();
		this.PRODUCT_ID = PRODUCT_ID;
		this.PRODUCT_NAME = PRODUCT_NAME;
		this.PRODUCT_TYPE = PRODUCT_TYPE;
		this.PRODUCT_PRICE = PRODUCT_PRICE;
		this.PRODUCT_QUANTITY = PRODUCT_QUANTITY;
		this.PRODUCT_DESCRIPTION = PRODUCT_DESCRIPTION;
		this.PRODUCT_OUTBOUNDTIME = PRODUCT_OUTBOUNDTIME;
	}
	public Outbound_Entity(int pRODUCT_ID, String pRODUCT_NAME, String pRODUCT_TYPE, float pRODUCT_PRICE,
			String pRODUCT_SUPPLER, String pRODUCT_DESCRIPTION, Date pRODUCT_OUTBOUNDTIME, int pRODUCT_QUANTITY) {
		super();
		PRODUCT_ID = pRODUCT_ID;
		PRODUCT_NAME = pRODUCT_NAME;
		PRODUCT_TYPE = pRODUCT_TYPE;
		PRODUCT_PRICE = pRODUCT_PRICE;
		PRODUCT_SUPPLER = pRODUCT_SUPPLER;
		PRODUCT_DESCRIPTION = pRODUCT_DESCRIPTION;
		PRODUCT_OUTBOUNDTIME = pRODUCT_OUTBOUNDTIME;
		PRODUCT_QUANTITY = pRODUCT_QUANTITY;
	}
	
	
	
}
