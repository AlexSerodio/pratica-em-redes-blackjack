package model;

public class Card {

    public int number;
    public String suit;

    public Card(String number, String suit) {
        if("J".equals(number) || "Q".equals(number) || "K".equals(number))
            this.number = 10;
        else if("A".equals(number))
            this.number = 1;
        else
            this.number = Integer.parseInt(number);
        
        this.suit = suit;
    }

    @Override
    public String toString() {
        return number + "-" + suit;
    }

}
