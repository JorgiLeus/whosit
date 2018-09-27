package com.axxes.whosit.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Staff staff;

    @Column
    private boolean correct;

    @Column
    private boolean isCompleted;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Staff.class, cascade = CascadeType.PERSIST)
    private List<Staff> possibleStaff;

    @Transient
    private final int amountPossibleAnswers;

    public boolean isCompleted() {
        return isCompleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Staff> getPossibleStaff() {
        return possibleStaff;
    }

    public void setPossibleStaff(List<Staff> possibleStaff) {
        this.possibleStaff = possibleStaff;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Round(){
        this.amountPossibleAnswers = 4;
        possibleStaff = new ArrayList<>(amountPossibleAnswers-1);
    }

    public Round(Staff staff){
        this();
        this.staff = staff;
    }

    public Round(Staff staff, int amountPossibleAnswers){
        this.amountPossibleAnswers = amountPossibleAnswers;
        this.staff = staff;
    }


    public boolean isCorrect(){
        return correct;
    }

    public void checkAnswer(String staffId){
        if(!isCompleted){
            this.correct = this.staff.getId().equals(staffId);
            this.isCompleted = true;
        }
    }

    public String getCorrectAnswer(){
        return staff.getId();
    }

    public void randomValues(List<Staff> staffList){
        List<Staff> possibleValues = staffList.stream()
                .filter(st -> staff.getGender() == st.getGender()&& !st.getId().equals(staff.getId()))
                .collect(Collectors.toList());

        possibleStaff = randomSubList(possibleValues);
    }

    private List<Staff> randomSubList(List<Staff> sourceStaffs){
        Random random = new Random();
        int randomIndex = random.nextInt(sourceStaffs.size()-(amountPossibleAnswers-1));
        return sourceStaffs.subList(randomIndex, randomIndex+amountPossibleAnswers);
    }

    public List<Staff> getPossibleAnswers(){
        List<Staff> answers = new ArrayList<>(possibleStaff);
        answers.add(staff);
        Collections.shuffle(answers);
        return answers;
    }
}
