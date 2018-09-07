package com.axxes.whosit.controllers;

import com.axxes.whosit.domain.AxxesUser;
import com.axxes.whosit.domain.Game;
import com.axxes.whosit.domain.Staff;
import com.axxes.whosit.service.GameService;
import com.axxes.whosit.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private GameService gameService;
    private StaffService staffService;

    @Autowired
    public ScoreController(GameService gameService, StaffService staffService){
        this.gameService = gameService;
        this.staffService = staffService;
    }

    @GetMapping("/personalscore/{staffId}/{gameId}")
    public ResponseEntity<List<Game>> getCurrentScoreAndMaxScore(@PathVariable("staffId") final Long staffId, @PathVariable("gameId") final Long gameId, HttpServletRequest request){
        List<Game> games = new ArrayList<>();

        Optional<Staff> currentStaff = staffService.getStaffById(staffId);

        Optional<Game> bestGame = gameService.getBestGameForAxxesUser(currentStaff.get().getId(), gameId);
        Optional<Game> currentGame = gameService.getGameById(gameId);

        if(currentGame.isPresent()){
            games.add(currentGame.get());
        }
        if (bestGame.isPresent()){
            games.add(bestGame.get());
        }

        if (games != null){
            return ResponseEntity.ok(games);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/hiscores")
    public ResponseEntity<List<Game>> getHiScores(){
        List<Game> games = gameService.getHiScores();
        if (games != null){
            return ResponseEntity.ok(games);
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
