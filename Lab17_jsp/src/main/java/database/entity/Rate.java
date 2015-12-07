package database.entity;

public class Rate extends BaseEntity {
	private int id;
	private Character rateValue;
	
	public Rate(int rateId, Character rateValue){
		this.id = rateId;
		this.rateValue = rateValue;
	}

	@Override
	public int getId() {		
		return id;
	}
	
	public Character getRateValue(){
		return rateValue;
	}
	
	public void setId(int rateId) {		
		this.id = rateId;
	}
	
	public void setStatusName(Character rateValue){
		this.rateValue = rateValue;
	}
}
