package com.axxes.whosit.persistence.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Round[] rounds;
    private static final int standardNumberRounds = 20;
    private int currentRound = 0;

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
        Random random = new Random();
        for(int i = 0; i < rounds.length; i++){
            int randomValue = random.nextInt(staffs.size());
            Staff randomStaff = staffs.get(randomValue);
            rounds[i] = new Round(randomStaff);
        }
    }

    public boolean isCorrect(int round, Long staffId){
        return rounds[round].isCorrect();
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
        return rounds[currentRound];
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

}
