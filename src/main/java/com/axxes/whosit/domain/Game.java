package com.axxes.whosit.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(targetEntity = Round.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private List<Round> rounds;

    /*@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "axxes_user_id")
    private AxxesUser axxesUser;*/

    @Column
    @CreatedDate
    private Date timestamp;

    @Column
    private double score;

    @Column(name =  "completiontime")
    private long completionTimeMs;

    public Game(List<Staff> staffs){
        this(staffs, 20);
    }

    public Game(List<Staff> staffs, int numberRounds){
        rounds = new ArrayList<>();
        timestamp = Date.from(Instant.now());
        generateRandomAnswers(staffs, numberRounds);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
/*
    public AxxesUser getAxxesUser() {
        return axxesUser;
    }

    public void setAxxesUser(AxxesUser axxesUser) {
        this.axxesUser = axxesUser;
    }*/

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
