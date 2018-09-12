package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameResponse {

    private long id;

    private boolean tryout;

    @JsonProperty("user")
    private long staffId;

    private int rounds;

    private int currentRoundIndex;

    public GameResponse() {
    }

    public GameResponse(long id, long staffId, int rounds, int currentRoundIndex) {
        this(id, staffId, rounds, currentRoundIndex, false);
    }

    public GameResponse(long id, long staffId, int rounds, int currentRoundIndex, boolean tryout) {
        this.id = id;
        this.tryout = tryout;
        this.staffId = staffId;
        this.rounds = rounds;
        this.currentRoundIndex = currentRoundIndex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getCurrentRoundIndex() {
        return currentRoundIndex;
    }

    public void setCurrentRoundIndex(int currentRoundIndex) {
        this.currentRoundIndex = currentRoundIndex;
    }
}
