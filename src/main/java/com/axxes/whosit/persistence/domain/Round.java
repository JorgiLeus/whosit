package com.axxes.whosit.persistence.domain;

public class Round {
    private Staff staff;
    private boolean correct;

    public boolean isCompleted() {
        return isCompleted;
    }

    private boolean isCompleted;

    public Round(Staff staff){
        this.staff = staff;
    }
    public boolean isCorrect(){
        return correct;
    }

    public void setCorrect(Staff staff){
        this.correct = this.staff.getId() == staff.getId();
        isCompleted = true;
    }
}
