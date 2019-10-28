package model;

import java.nio.charset.Charset;

public class User {

    public String id;
    public String name;
    public int wins;

    public User(String id, String name, String wins) {
        this.id = id;
        this.name = new String(name.getBytes(), Charset.forName("UTF-8"));
        this.wins = Integer.valueOf(wins);
    }

    @Override
    public String toString() {
        return name + ", " + id + " - " + wins + " vitórias";
    }

}
