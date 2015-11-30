package database.entity;

public class Subject extends BaseEntity {

	private int id;
	private String name;
	private int termId;
	
	public Subject(int subjectId, String name, int termId){
		this.id = subjectId;
		this.name = name;
		this.termId = termId;
	}

	@Override
	public int getId() {		
		return id;
	}
	
	public void setId(int subjectId) {		
		this.id = subjectId;
	}
	
	public String getName(){
		return name;
	}	
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getTermId(){
		return termId;
	}

	public void setTermId(int termId) {		
		this.termId = termId;
	}
	
}
