package com.axxes.whosit.controllers;

import com.axxes.whosit.domain.AxxesUser;
import com.axxes.whosit.domain.Game;
import com.axxes.whosit.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private GameService gameService;

    @Autowired
    public ScoreController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/personalscore")
    public ResponseEntity<List<Game>> getScore(@RequestBody AxxesUser axxesUser, @RequestBody Game game, HttpServletRequest request){
        List<Game> games = new ArrayList<>();
        Game bestGame = gameService.getBestGameForAxxesUser(axxesUser);

        games.add(game);
        games.add(bestGame);

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
