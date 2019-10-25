package model;

public class Card {

	public int number;
	public String suit;
	
	public Card(String number, String suit) {
		this.number = Integer.valueOf(number);
		this.suit = suit;
	}
	
	@Override
	public String toString() {
		return "{ number: " + number + ", suit: " + suit + " }";
	}
	
}
