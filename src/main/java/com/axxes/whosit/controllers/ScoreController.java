package com.axxes.whosit.controllers;

import com.axxes.whosit.domain.Game;
import com.axxes.whosit.domain.GameScore;
import com.axxes.whosit.domain.Staff;
import com.axxes.whosit.service.GameService;
import com.axxes.whosit.service.StaffService;
import com.axxes.whosit.view.RankView;
import com.axxes.whosit.view.ScoreView;
import com.axxes.whosit.view.StaffView;
import com.axxes.whosit.view.ScoreListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ScoreController {

    private GameService gameService;
    private StaffService staffService;

    @Autowired
    public ScoreController(GameService gameService, StaffService staffService){
        this.gameService = gameService;
        this.staffService = staffService;
    }

    @GetMapping("scores/{staffId}/{gameId}")
    public ResponseEntity<RankView> getCurrentScoreAndMaxScore(
            @PathVariable("staffId") final String staffId,
            @PathVariable(value = "gameId", required = false) final Long gameId,
            HttpServletRequest request){

        Optional<Staff> currentStaff = staffService.getStaffById(staffId);

        Optional<Game> bestGame = gameService.getBestGameForStaffUser(currentStaff.get().getId(), gameId);
        Optional<Game> currentGame = gameService.getGameById(gameId);

        /*Staff staff = currentGame.get().getStaff();
        StaffView staffView = new StaffView(staff.getId(), staff.getFullName(), staff.getGender().name());*/

        RankView rank = new RankView();

        /*rank.setBest(new ScoreView(staffView, bestGame.get().getScore(), bestGame.get().getCompletionTimeMs(), -1));
        rank.setCurrent(new ScoreView(staffView, currentGame.get().getScore(), currentGame.get().getCompletionTimeMs(), -1));*/
        rank.setRank(gameService.getBestRankForUser(staffId));

        if(currentGame.isPresent()){
            rank.setCurrent(gameToScoreView(currentGame.get()));
        } else {
            return ResponseEntity.notFound().build();
        }

        rank.setBest(gameToScoreView(currentGame.orElse(null)));

        return ResponseEntity.ok(rank);

    }

    @GetMapping("/scores")
    public ResponseEntity<List<GameScore>> getHiScores(){
        List<GameScore> games = gameService.getGameScore();

        if (games == null){
            games = new ArrayList<>();
        }
        System.out.println(games);

       List<ScoreView> scores = games.stream()
                .map(this::gameScoreToScoreView)
                .collect(Collectors.toList());

        ScoreListView scoreListView = new ScoreListView("period todo", scores);

        return ResponseEntity.ok(games);
    }

    private ScoreView gameToScoreView(Game game) {
        Staff staff = game.getStaff();
        StaffView staffView = new StaffView(staff.getId(), staff.getFullName(), staff.getGender().name());
        return new ScoreView(staffView, game.getScore(), game.getCompletionTimeMs(), -1);
    }
    private ScoreView gameScoreToScoreView(GameScore gameScore){
        Staff staff = gameScore.getStaff();
        StaffView staffView = new StaffView(staff.getId(), staff.getFullName(), staff.getGender().name());
        return new ScoreView(staffView, gameScore.getScore(), gameScore.getCompletionTimeMs(), gameScore.getAttempts());
    }
}
