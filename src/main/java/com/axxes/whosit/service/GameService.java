package com.axxes.whosit.service;

import com.axxes.whosit.domain.Game;
import com.axxes.whosit.domain.GameScore;
import com.axxes.whosit.domain.Staff;

import java.util.List;
import java.util.Optional;


public interface GameService {
    Optional<Game> getGameById(Long id);
    Long createGame(Game game);
    List<GameScore> getGameScore();
    Optional<Game> getBestGameForStaffUser(String id, Long game_id);
    void update(Game game);
    int getBestRankForUser(String staff_id);
}
