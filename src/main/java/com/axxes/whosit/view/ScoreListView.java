package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ScoreListView {
    private String period;

    @JsonProperty("scores")
    private List<ScoreView> topscores;

    public ScoreListView() {
    }

    public ScoreListView(String period, List<ScoreView> topscores) {
        this.period = period;
        this.topscores = topscores;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public List<ScoreView> getTopscores() {
        return topscores;
    }

    public void setTopscores(List<ScoreView> topscores) {
        this.topscores = topscores;
    }
}
