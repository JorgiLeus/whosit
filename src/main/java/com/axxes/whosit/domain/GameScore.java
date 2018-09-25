package com.axxes.whosit.domain;

import java.util.Date;

public class GameScore {

    private String staffName;

    private double score;

    private long completionTimeMs;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    private Date timestamp;

    public GameScore(){}

    public GameScore(String staffName, double score, long completionTimeMs, Date timeStamp){
        this.staffName = staffName;
        this.score = score;
        this.completionTimeMs = completionTimeMs;
        this.timestamp = timeStamp;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaff(String staffName) {
        this.staffName = staffName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public long getCompletionTimeMs() {
        return completionTimeMs;
    }

    public void setCompletionTimeMs(long completionTimeMs) {
        this.completionTimeMs = completionTimeMs;
    }
}
