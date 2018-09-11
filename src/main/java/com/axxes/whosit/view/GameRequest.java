package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameRequest {
    @JsonProperty("rounds")
    private int numberOfRounds;

    private boolean tryout;

    @JsonProperty("userId")
    private long staffId;

    public GameRequest(){}

    public GameRequest(int numberOfRounds, long userId){
        this(numberOfRounds, userId,false);
    }

    public GameRequest(int numberOfRounds, long userId, boolean tryout){
        this.numberOfRounds = numberOfRounds;
        this.tryout = tryout;
    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public boolean isTryout() {
        return tryout;
    }

    public void setTryout(boolean tryout) {
        this.tryout = tryout;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }
}
