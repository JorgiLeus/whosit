package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RoundView {

    @JsonProperty("pictures")
    private List<PictureView> possibleAnswers;

    private Long id;

    private String fullName;

    private Long gameId;

    public RoundView(){}

    public RoundView (Long id, List<PictureView> possibleAnswers, String fullName, long gameId){
        this.id = id;
        this.possibleAnswers = possibleAnswers;
        this.fullName = fullName;
        this.gameId = gameId;
    }

    public List<PictureView> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<PictureView> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
