package entities;

public class Part extends AbstractEntity {

	private int part_id;
	private String part_name;
	private String vendor;
	private long amount;
	
	@Override
	public int getId() {
		return part_id;
	}
	
	@Override
	public String getValuesCommaSeparated() {
		return "'" + part_name + "', '" + vendor + "', " + amount;
	}
	
	public String getPartName() {
		return part_name;
	}
	
	public String getVendor() {
		return vendor;
	}
	
	public long getAmount() {
		return amount;
	}
	
	protected void setId(int value) {
		part_id = value;
	}
	
	public void setPartName(String value) {
		part_name = value;
	}
	
	public void setVendor(String value) {
		vendor = value;
	}
	
	public void setAmount(long value) {
		amount = value;
	}
	
	@Override
	public String toString() {
		String res = "part_name = '" + part_name + "', vendor = '" + 
				vendor + "', amount = " + amount;
		return res;
	}
	
	public Part(String part_name, String vendor, long amount) {
		this.part_name = part_name;
		this.vendor = vendor;
		this.amount = amount;
	}
	
	public Part() {
		this("", "", 0L);
	}
}
