package com.axxes.whosit.controllers;

import com.axxes.whosit.domain.Round;
import com.axxes.whosit.service.RoundService;
import com.axxes.whosit.view.RoundResponseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/API")
public class RoundController {
    private RoundService roundService;

    @Autowired
    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    @PutMapping("/round/{roundId}")
    public ResponseEntity<?> getCorrectAnswer(@PathVariable Long roundId, @RequestParam(name = "answer") String staffId){
        Optional<Round> optRound = roundService.findById(roundId);

        if(!optRound.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Round round = optRound.get();

        if(!round.isCompleted()){
            round.checkAnswer(staffId);
            roundService.update(round);
        }

        RoundResponseView roundResponseView = new RoundResponseView(round.getCorrectAnswer());

        return ResponseEntity.ok(roundResponseView);
    }
}
