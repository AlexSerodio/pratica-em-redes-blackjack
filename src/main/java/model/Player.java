package model;

public class Player {

	public String userId;
	public String status;
	
	public Player(String userId, String status) {
		this.userId = userId;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "{ id: " + userId + ", status: " + status + " }";
	}
	
}
