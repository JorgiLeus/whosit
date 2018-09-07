package com.axxes.whosit.service;

import com.axxes.whosit.domain.AxxesUser;
import com.axxes.whosit.domain.Game;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface GameService {
    Optional<Game> getGameById(Long id);
    Long createGame(Game game);
    List<Game> getHiScores();
    Game getBestGameForAxxesUser(AxxesUser axxesUser);
}
