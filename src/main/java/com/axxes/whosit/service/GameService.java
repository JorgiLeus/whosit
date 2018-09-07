package com.axxes.whosit.service;

import com.axxes.whosit.domain.Game;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface GameService {
    Optional<Game> getGameById(Long id);
    Long createGame(Game game);
}
