package com.axxes.whosit.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Round> rounds;

    public Game(AxxesUser user, List<Staff> staffs){
        this(user, staffs, 20);
    }

    public Game(AxxesUser user, List<Staff> staffs, int numberRounds){
        this.axxesUser = user;
        rounds = new ArrayList<>();
        generateRandomAnswers(staffs, numberRounds);
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "axxes_user_id")
    private AxxesUser axxesUser;

    @Column
    @CreatedDate
    private Date timestamp;

    @Column
    private double score;

    @Column(name =  "completiontime")
    private long completionTimeMs;

    public void generateRandomAnswers(List<Staff> staffs, int numberOfRounds){
        Collections.shuffle(staffs);
        for(int i = 0; i < numberOfRounds; i++){
            Staff randomStaff = staffs.get(i);
            rounds.add(new Round(randomStaff, staffs));
        }
    }

    public boolean isCorrect(int round, Long staffId){
        rounds.get(round).setCorrect(staffId);
        return rounds.get(round).isCorrect();
    }

    public void calculateScore(){
        int correctAnswers = 0;
        for (Round round :
                rounds) {
            if(round.isCorrect()){
                ++correctAnswers;
            }
        }
        score = (double) correctAnswers/ rounds.size();
    }

    public Round getRound(int round){
        return rounds.get(round);
    }

    public int getAmountRoundNumber(){
        return rounds.size();
    }

    public boolean isCompleted(){
        for (Round r:
             rounds) {
            if(!r.isCompleted()){
             return false;
            }
        }
        return true;
    }

    //TODO: timecheck errors
    public void calculateCompletiontime(Date endDate) {
        this.completionTimeMs = endDate.getTime() - timestamp.getTime();
    }
}
