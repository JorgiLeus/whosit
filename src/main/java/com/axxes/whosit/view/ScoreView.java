package com.axxes.whosit.view;

import com.axxes.whosit.domain.Staff;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ScoreView {
    private StaffView staff;

    private String score;

    @JsonProperty("durationMs")
    private long completionTimeMs;

    private int attempts;

    public ScoreView() {
    }

    public ScoreView(StaffView staff, double score, long completionTimeMs, int attempts, int scoreScale) {
        this.staff = staff;
        this.score = (int)(score * scoreScale) + "/" + scoreScale;
        this.completionTimeMs = completionTimeMs;
        this.attempts = attempts;
    }

    public StaffView getStaff() {
        return staff;
    }

    public void setStaff(StaffView staff) {
        this.staff = staff;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public long getCompletionTimeMs() {
        return completionTimeMs;
    }

    public void setCompletionTimeMs(long completionTimeMs) {
        this.completionTimeMs = completionTimeMs;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}
