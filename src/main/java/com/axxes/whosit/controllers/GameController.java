package com.axxes.whosit.controllers;

import com.axxes.whosit.service.RoundService;
import com.axxes.whosit.view.GameRequest;
import com.axxes.whosit.view.RoundResponseView;
import com.axxes.whosit.view.RoundView;
import com.axxes.whosit.view.StaffView;
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
@RequestMapping("/API")
public class GameController {
    private GameService gameService;
    private StaffService staffservice;

    @Autowired
    public GameController(GameService gameService, StaffService staffService){
        this.gameService = gameService;
        this.staffservice = staffService;
    }

    @PostMapping("/game/create")
    public ResponseEntity<?> createGame(@RequestBody GameRequest gameRequest, HttpServletRequest request){
        List<Staff> staffList = staffservice.getAll();
        Long gameId = gameService.createGame(new Game(staffList, gameRequest.getNumberOfRounds()));

        URI url = ServletUriComponentsBuilder.fromRequest(request)
                .path("/{gameId}/round/1")
                .buildAndExpand(gameId)
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(url);

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/game/{gameId}/round/{roundIndex}")
    public ResponseEntity<?> getGameFirstRound(@PathVariable Long gameId, @PathVariable int roundIndex){
        List<Staff> staffList = staffservice.getAll();
        Optional<Game> optionalGame = gameService.getGameById(gameId);

        if(!optionalGame.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        Round requestedRound =  optionalGame.get().getRound(roundIndex);

        List<StaffView> possibleStaffview = new ArrayList<>();
        for (Staff s :
                requestedRound.getScrambledList()) {
            possibleStaffview.add(new StaffView(s.getId(), s.getPictureUrl()));
        }
        RoundView roundView = new RoundView(requestedRound.getId(), possibleStaffview, requestedRound.getStaff().getFullName());

        return ResponseEntity.ok(roundView);
    }



}
