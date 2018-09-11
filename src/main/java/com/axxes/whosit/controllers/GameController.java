package com.axxes.whosit.controllers;

import com.axxes.whosit.view.*;
import com.axxes.whosit.domain.*;
import com.axxes.whosit.service.GameService;
import com.axxes.whosit.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GameController {
    private GameService gameService;
    private StaffService staffservice;

    @Autowired
    public GameController(GameService gameService, StaffService staffService){
        this.gameService = gameService;
        this.staffservice = staffService;
    }

    @PostMapping("/game")
    public ResponseEntity<?> createGame(@RequestBody GameRequest gameRequest, HttpServletRequest request){
        //TODO: implement tryout
        List<Staff> staffList = staffservice.getAll();
        Optional<Staff> optionalStaff = staffservice.getStaffById(gameRequest.getStaffId());

        if(!optionalStaff.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Long gameId = gameService.createGame(new Game(staffList, gameRequest.getNumberOfRounds(), optionalStaff.get()));

        URI url = ServletUriComponentsBuilder.fromRequest(request)
                .path("/{gameId}")
                .buildAndExpand(gameId)
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(url);

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<?>  getGame(@PathVariable Long gameId) {
        Optional<Game> optionalGame = gameService.getGameById(gameId);

        if(!optionalGame.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Game game = optionalGame.get();

        //TODO: set currentRoundIndex by rounds that have been played
        //TODO: implement tryout;
        GameResponse gameResponse = new GameResponse(game.getId(),
                game.getStaff().getId(),
                game.getAmountRoundNumber(),
                0);

        return ResponseEntity.ok(gameResponse);
    }

    @GetMapping("/game/{gameId}/round/{roundIndex}")
    public ResponseEntity<?> getRoundByRoundIndex(@PathVariable Long gameId, @PathVariable int roundIndex){
        Optional<Game> optionalGame = gameService.getGameById(gameId);

        if(!optionalGame.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Game game = optionalGame.get();
        Round requestedRound =  game.getRound(roundIndex);

        List<PictureView> possibleStaffview = new ArrayList<>();
        for (Staff s :
                requestedRound.getScrambledList()) {
            possibleStaffview.add(new PictureView(s.getId(), s.getPictureUrl()));
        }
        RoundView roundView = new RoundView(
                requestedRound.getId(),
                possibleStaffview,
                requestedRound.getStaff().getFullName(),
                game.getId());

        return ResponseEntity.ok(roundView);
    }



}
