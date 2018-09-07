package com.axxes.whosit.view;

public class GameRequest {

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public GameRequest(){}

    private int numberOfRounds;
    public GameRequest(int numberOfRounds){
        this.numberOfRounds = numberOfRounds;
    }
}
