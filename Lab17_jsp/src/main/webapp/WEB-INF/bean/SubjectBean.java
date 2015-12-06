package bean;

public class SubjectBean {
	private int id;
	private String name;
	private String term;
	
	public SubjectBean(int id, String name, String term){
		this.id=id;
		this.name=name;
		this.term=term;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
}
