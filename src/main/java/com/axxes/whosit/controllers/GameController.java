package com.axxes.whosit.controllers;

import com.axxes.whosit.GameRequest;
import com.axxes.whosit.domain.AxxesUser;
import com.axxes.whosit.domain.Game;
import com.axxes.whosit.domain.Gender;
import com.axxes.whosit.domain.Staff;
import com.axxes.whosit.service.GameService;
import com.axxes.whosit.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

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

//        URI url = UriComponentsBuilder.fromHttpUrl(request.getRequestURI()).path("game/{gameId}/round/1")
//                .buildAndExpand(gameId).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(url);

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/game/{gameId}/round/{round}")
    public ResponseEntity<?> getGameFirstRound(@PathVariable Long gameId, HttpServletRequest request){
        Optional<Game> optionalGame = gameService.getGameById(gameId);
        return null;
    }
}
