package com.axxes.whosit.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import static java.time.temporal.ChronoUnit.MILLIS;

@Entity
@Table(name = "game")
public class Game implements ScoreComparable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(targetEntity = Round.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private List<Round> rounds;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name="staff_id")
    private Staff staff;

    @Column
    @CreatedDate
    private LocalDateTime timestamp;

    @Column
    private double score;

    @Column(name = "completiontime")
    private long completionTimeMs;

    @Transient
    private boolean isCompleted;

    public Game() {}

    public Game(List<Staff> staffs, Staff staff){
        this(staffs, 20, staff);
    }

    public Game(List<Staff> staffs, int numberRounds, Staff staff){
        rounds = new ArrayList<>();
        this.timestamp = LocalDateTime.now();
        this.staff = staff;
        generateAnswers(staffs, numberRounds);
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
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

    public int getCompletedRounds() {
        return (int) this.rounds.stream().filter(r -> r.isCompleted()).count();
    }

    public void generateAnswers(List<Staff> staffs, int numberOfRounds){
        Collections.shuffle(staffs);
        for(int i = 0; i < numberOfRounds; i++){
            Staff randomStaff = staffs.get(i);
            Round r = new Round(randomStaff);
            r.randomValues(staffs);
            rounds.add(r);
        }
    }

    public boolean isCompleted(){
        return !(rounds.stream().anyMatch(r -> !r.isCompleted()));
    }

    public void endGame(){
        if(!isCompleted()){
            throw new RuntimeException("Game Must be finished before ending the game");
        }
        int correctAnswers = 0;
        for (Round round :
                rounds) {
            if(round.isCorrect()){
                ++correctAnswers;
            }
        }
        score = (double) correctAnswers/ rounds.size();
        this.calculateCompletiontime(LocalDateTime.now());
    }

    public Optional<Round> getNextRound(){
        return rounds.stream().filter(r -> !r.isCompleted()).findFirst();
    }

    public int getAmountRoundNumber(){
        return rounds.size();
    }


    private void calculateCompletiontime(LocalDateTime endDate) {
        if(!timestamp.isBefore(endDate)){
            throw new IllegalArgumentException("EndDate Can't be before start of the game");
        }
        this.completionTimeMs = MILLIS.between(timestamp, endDate);
    }
}
