package model;
public class User {

	public String id;
	public String name;
	public int wins;
	
	public User(String id, String name, String wins) {
		this.id = id;
		this.name = name;
		this.wins = Integer.valueOf(wins);
	}
	
	@Override
	public String toString() {
		return "{ id: " + id + ", name: " + name + ", wins: " + wins + " }";
	}
	
}
