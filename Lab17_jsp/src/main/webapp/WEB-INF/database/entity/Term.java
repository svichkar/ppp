package database.entity;

public class Term extends BaseEntity {

	private int id;
	private String alias;
	
	public Term(int termId, String alias){
		this.id = termId;
		this.alias = alias;
	}

	@Override
	public int getId() {		
		return id;
	}
	
	public String getAlias(){
		return alias;
	}
	
	public void setId(int termId) {		
		this.id = termId;
	}
	
	public void setAlias(String alias){
		this.alias = alias;
	}
}
