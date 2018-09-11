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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="staff_id")
    private Staff staff;

    @Column
    @CreatedDate
    private Date timestamp;

    @Column
    private double score;

    @Column(name =  "completiontime")
    private long completionTimeMs;

    public Game() {}

    public Game(List<Staff> staffs, Staff staff){
        this(staffs, 20, staff);
    }

    public Game(List<Staff> staffs, int numberRounds, Staff staff){
        rounds = new ArrayList<>();
        timestamp = Date.from(Instant.now());
        this.staff = staff;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void generateRandomAnswers(List<Staff> staffs, int numberOfRounds){
        Collections.shuffle(staffs);
        for(int i = 0; i < numberOfRounds; i++){
            Staff randomStaff = staffs.get(i);
            rounds.add(new Round(randomStaff, staffs));
        }
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
