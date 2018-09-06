package com.axxes.whosit.persistence.domain;

import java.util.Objects;

public class Round {
    private Staff staff;

    public boolean isCorrect() {
        return correct;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    private boolean correct;

    public boolean isCompleted() {
        return isCompleted;
    }

    private boolean isCompleted;

    public Round(Staff staff){
        this.staff = staff;
    }

    public boolean isCorrect(Long staffId){
        this.correct = staff.getId() == staffId;
        return staff.getId() == staffId;
    }

    public void setCorrect(Staff staff){
        this.correct = this.staff.getId() == staff.getId();
        isCompleted = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return correct == round.correct &&
                isCompleted() == round.isCompleted() &&
                Objects.equals(getStaff(), round.getStaff());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStaff(), correct, isCompleted());
    }
}
