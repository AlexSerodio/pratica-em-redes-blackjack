package model;
public class Message {

	public String userId;
	public String content;
	
	public Message(String userId, String content) {
		this.userId = userId;
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "{ userId: " + userId + ", content: " + content + " }";
	}
	
}
