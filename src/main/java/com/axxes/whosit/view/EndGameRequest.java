package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EndGameRequest {
    private long gameId;

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public EndGameRequest(){}

    public EndGameRequest(long gameId){
        this.gameId = gameId;
    }
}
