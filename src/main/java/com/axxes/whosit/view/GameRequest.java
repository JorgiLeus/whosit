package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameRequest {
    @JsonProperty("rounds")
    private int numberOfRounds;

    @JsonProperty("tryout")
    private boolean tryout;

    @JsonProperty("userId")
    private String staffId;

    public GameRequest(){}

    public GameRequest(int numberOfRounds, String userId){
        this(numberOfRounds, userId,false);
    }

    public GameRequest(int numberOfRounds, String userId, boolean tryout){
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

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
