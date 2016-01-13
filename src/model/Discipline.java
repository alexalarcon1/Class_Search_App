package model;

public class Discipline {
	private String name;
	private static final String query = "select discipline_name " +
										"from DISCIPLINE_SR " +
										"order by discipline_name asc";
	public Discipline() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getQuery() {
		return query;
	}
}
