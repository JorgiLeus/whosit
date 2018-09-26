package com.axxes.whosit.service.impl;

import com.axxes.whosit.domain.Game;
import com.axxes.whosit.domain.GameScore;
import com.axxes.whosit.domain.Staff;
import com.axxes.whosit.repository.GameRepository;
import com.axxes.whosit.repository.StaffRepository;
import com.axxes.whosit.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private GameRepository gameRepo;
    private StaffRepository staffRepo;

    @Autowired
    public GameServiceImpl(GameRepository gameRepo, StaffRepository staffRepo){
        this.gameRepo = gameRepo;
        this.staffRepo = staffRepo;
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
    public List<GameScore> getGameScore() {
        return gameRepo.getGameScores();
    }

    @Override
    public Optional<Game> getBestGameForStaffUser(String staff_id, Long id) {
        return gameRepo.findFirstByStaff_idAndIdNotOrderByScoreDescCompletionTimeMsAsc(staff_id, id);
    }

    @Override
    public int getBestRankForUser(String staff_id) {
        Staff staff = staffRepo.getOne(staff_id);
        List<Game> games = gameRepo.findDistinctStaff_idByOrderByScoreDescCompletionTimeMsAsc();
        int rank = games.stream()
                .map(g -> g.getStaff())
                .collect(Collectors.toList())
                .indexOf(staff);
        rank++;

//        List<GameScore> gameList = gameRepo.getGameScores();
//        int rank = gameList.stream().map(g -> g.getStaffName())
//                .collect(Collectors.toList())
//                .indexOf(staff.getFirstName());
//        rank++;

        return rank;
    }

}
