package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameResponse {

    private long id;

    private boolean tryout;

    private int rounds;

    private int currentRound;

    public GameResponse() {
    }

    public GameResponse(long id, int rounds, int currentRoundIndex) {
        this(id, rounds, currentRoundIndex, false);
    }

    public GameResponse(long id, int rounds, int currentRoundIndex, boolean tryout) {
        this.id = id;
        this.tryout = tryout;
        this.rounds = rounds;
        this.currentRound = currentRoundIndex;
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

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRoundIndex) {
        this.currentRound = currentRoundIndex;
    }
}
