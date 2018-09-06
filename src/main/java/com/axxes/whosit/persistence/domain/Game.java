package com.axxes.whosit.persistence.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Round[] rounds;
    private static final int standardNumberRounds = 20;
    private int currentRound = 0;

    public Game(){}

    public Game(AxxesUser user, List<Staff> staffs){
        this(user, staffs, standardNumberRounds);
    }

    public Game(AxxesUser user, List<Staff> staffs, int numberRounds){
        this.axxesUser = user;
        rounds = new Round[numberRounds];
        generateRandomAnswers(staffs);
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

    public void generateRandomAnswers(List<Staff> staffs){
        Collections.shuffle(staffs);
        for(int i = 0; i < rounds.length; i++){
            Staff shuffledStaff = staffs.get(i);
            rounds[i] = new Round(shuffledStaff);
        }
    }

    public boolean isCorrect(int round, Long staffId){
        return rounds[round].isCorrect(staffId);
    }

    public void calculateScore(){
        int correctAnswers = 0;
        for (Round round :
                rounds) {
            if(round.isCorrect()){
                ++correctAnswers;
            }
        }
        score = (double) correctAnswers/ rounds.length;
    }

    public Round getRound(int round){
        return rounds[round];
    }

    public int getAmountRoundNumber(){
        return rounds.length;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Round[] getRounds() {
        return rounds;
    }

    public void setRounds(Round[] rounds) {
        this.rounds = rounds;
    }

    public static int getStandardNumberRounds() {
        return standardNumberRounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public AxxesUser getAxxesUser() {
        return axxesUser;
    }

    public void setAxxesUser(AxxesUser axxesUser) {
        this.axxesUser = axxesUser;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return getId() == game.getId() &&
                Double.compare(game.getScore(), getScore()) == 0 &&
                getCompletionTimeMs() == game.getCompletionTimeMs() &&
                Objects.equals(getAxxesUser(), game.getAxxesUser()) &&
                Objects.equals(getTimestamp(), game.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAxxesUser(), getTimestamp(), getScore(), getCompletionTimeMs());
    }
}
