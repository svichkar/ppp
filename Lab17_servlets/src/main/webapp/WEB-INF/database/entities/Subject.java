package database.entities;

public class Subject extends BaseEntity {

	private int subjectId;
	private String name;
	private int termId;
	
	public Subject(int subjectId, String name, int termId){
		this.subjectId = subjectId;
		this.name = name;
		this.termId = termId;
	}

	@Override
	public int getId() {		
		return subjectId;
	}
	
	public void setId(int subjectId) {		
		this.subjectId = subjectId;
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
