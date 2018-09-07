package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RoundView {

    @JsonProperty("pictures")
    private List<StaffView> possibleAnswers;
    @JsonProperty("roundId")
    private Long id;
    @JsonProperty("fullname")
    private String fullName;

    public RoundView(){}

    public RoundView (Long id, List<StaffView> possibleAnswers, String fullName){
        this.id = id;
        this.possibleAnswers = possibleAnswers;
        this.fullName = fullName;
    }

    public List<StaffView> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<StaffView> possibleAnswers) {
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
}
