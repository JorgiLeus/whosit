package com.axxes.whosit.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    @Transient
    private List<Staff> possibleStaff;

    public boolean isCompleted() {
        return isCompleted;
    }

    private boolean isCompleted;

    public Round(Staff staff, List<Staff> stafList){
        this.staff = staff;
        possibleStaff = new ArrayList<>();
//        generatePossibleStaff(stafList);
    }

    public boolean isCorrect(){
        return correct;
    }

    public void setCorrect(long staffId){
        this.correct = this.staff.getId() == staffId;
        isCompleted = true;
    }

    public void generatePossibleStaff(List<Staff> staffList){
        List<Staff> sameKindOfStaffList = staffList.stream().filter(st -> staff.getGender() == st.getGender()&& st.getId() != staff.getId()).collect(Collectors.toList());
        Collections.shuffle(sameKindOfStaffList);
        for(int i = 0; i < 3; i++){
            possibleStaff.add(sameKindOfStaffList.get(i));
        }
    }
}
