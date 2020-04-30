package com.example.cleaningthecity;

public class Player  {
    //private int rank;
    private int score;
    private String name;

    public Player(int score, String name) {
        //this.rank=rank;
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
