package com.axxes.whosit.domain;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "round")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Staff staff;
    @Column
    private boolean correct;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Staff> possibleStaff;

    public boolean isCompleted() {
        return isCompleted;
    }

    private boolean isCompleted;

    public Round(Staff staff, List<Staff> stafList){
        this.staff = staff;
        generatePossibleStaff(stafList);
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
