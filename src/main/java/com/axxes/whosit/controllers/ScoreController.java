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

        RankView rank = new RankView();

        //todo: get ranking for user
        rank.setRank(-1);

        if(currentGame.isPresent()){
            rank.setCurrent(gameToScoreView(currentGame.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
        if (bestGame.isPresent()){
            rank.setBest(gameToScoreView(currentGame.get()));
        }

        return ResponseEntity.ok(rank);

    }

   /* @GetMapping("/scores")
    public ResponseEntity<ScoreListView> getHiScores(){
        List<GameScore> games = gameService.getGameScore();


        if (games == null){
            games = new ArrayList<>();

        }

        List<ScoreView> scores = games.stream()
                .map(g -> gameToScoreView(g))
                .collect(Collectors.toList());

        ScoreListView scoreListView = new ScoreListView("period todo", scores);

        return ResponseEntity.ok(scoreListView);
    }*/

    private ScoreView gameToScoreView(Game game) {
        Staff staff = game.getStaff();
        StaffView staffView = new StaffView(staff.getId(), staff.getFullName(), staff.getGender().name());
        return new ScoreView(staffView, game.getScore(), game.getCompletionTimeMs(), -1);
    }
}
