package com.axxes.whosit.controllers;

import com.axxes.whosit.domain.AxxesUser;
import com.axxes.whosit.domain.Game;
import com.axxes.whosit.domain.Gender;
import com.axxes.whosit.domain.Staff;
import com.axxes.whosit.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/API")
public class GameController {
    private GameService gameService;

    private static final List<Staff> staffList = new ArrayList<>();
    {
        for (int i = 0; i <= 50; i++){
            Staff staff = new Staff();
            staff.setId(i+1);
            staff.setFirstName("Naam" + i);
            staff.setLastName("Achternaam" + i);
            staff.setGender( i%2==0? Gender.MALE : Gender.FEMALE);
            staff.setPictureUrl("test"+i);
            staffList.add(staff);
        }
    }

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @PostMapping("game/create")
    public void createGame(){
        AxxesUser user = new AxxesUser();
        user.setStaff(staffList.get(0));
        gameService.createGame(new Game(user, staffList));
    }
}
