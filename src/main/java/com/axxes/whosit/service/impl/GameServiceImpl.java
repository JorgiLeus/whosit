package com.axxes.whosit.service.impl;

import com.axxes.whosit.domain.AxxesUser;
import com.axxes.whosit.domain.Game;
import com.axxes.whosit.domain.Staff;
import com.axxes.whosit.repository.GameRepository;
import com.axxes.whosit.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private GameRepository gameRepo;

    @Autowired
    public GameServiceImpl(GameRepository gameRepo){
        this.gameRepo = gameRepo;
    }

    @Override
    public Optional<Game> getGameById(Long id) {
       return gameRepo.findById(id);
    }

    @Override
    public Long createGame(Game game) {
        Game savedGame = gameRepo.save(game);
        return savedGame.getId();
    }

    @Override
    public List<Game> getHiScores() {
        return gameRepo.findTop10ByOrderByScoreDescCompletionTimeMsAsc();
    }

    @Override
    public Optional<Game> getBestGameForAxxesUser(Long staff_id, Long id) {
        return gameRepo.findFirstByStaff_idAndIdNotOrderByScoreDescCompletionTimeMsAsc(staff_id, id);
    }
}
