package com.axxes.whosit.domain;

import java.time.LocalDateTime;

public class GameScore implements ScoreComparable {

    private Staff staff;

    private double score;

    private long completionTimeMs;

    private LocalDateTime timestamp;

    private int attempts;

    public GameScore(){
        this.attempts = 0;
    }

    public GameScore(Staff staff, double score, long completionTimeMs, LocalDateTime timeStamp){
        this.staff = staff;
        this.score = score;
        this.completionTimeMs = completionTimeMs;
        this.timestamp = timeStamp;
    }
    public GameScore(Game game, int attempts){
        this.staff = game.getStaff();
        this.score =  game.getScore();
        this.completionTimeMs = game.getCompletionTimeMs();
        this.timestamp = game.getTimestamp();
        this.attempts = attempts;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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

    public int getAttempts(){
        return this.attempts;
    }
}
