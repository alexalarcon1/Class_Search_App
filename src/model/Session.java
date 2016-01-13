package model;

public class Session {
	private static final String query = "select semester_name " +
										"from SEMESTER_SR";
	private String name;
	
	public Session(){
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuery() {
		return query;
	}
}
