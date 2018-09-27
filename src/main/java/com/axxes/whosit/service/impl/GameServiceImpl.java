package com.axxes.whosit.service.impl;

import com.axxes.whosit.domain.ScoreComparable;
import com.axxes.whosit.domain.Game;
import com.axxes.whosit.domain.GameScore;
import com.axxes.whosit.domain.Staff;
import com.axxes.whosit.repository.GameRepository;
import com.axxes.whosit.repository.StaffRepository;
import com.axxes.whosit.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private GameRepository gameRepo;
    private StaffRepository staffRepo;

    private static Comparator<ScoreComparable> scoreComperator = Comparator
            .comparing(ScoreComparable::getScore).reversed()
            .thenComparing(ScoreComparable::getCompletionTimeMs);

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
        LocalDate now = LocalDate.now();
        List<Game> matchingGames = gameRepo.findByCompletionTimeMsGreaterThanAndTimestampBetween(0, getFirstDayOfMonth(now), getLastDayOfMonth(now));
        Map<Staff, List<Game>> mappedByStaff = mapGamesByStaff(matchingGames);
        return highscores(mappedByStaff);
    }

    private Map<Staff, List<Game>> mapGamesByStaff(List<Game> games){
        return games.parallelStream()
                .collect(Collectors.groupingByConcurrent(Game::getStaff));
    }

    private List<GameScore> highscores(Map<Staff, List<Game>> mappedByStaff){
        return mappedByStaff.values()
                .stream()
                .map(this::findBestGame)
                .sorted(scoreComperator)
                .collect(Collectors.toList());
    }

    private GameScore findBestGame(List<Game> games) {
        Optional<Game> g = games.stream().min(scoreComperator);
        return new GameScore(g.orElse(null), games.size());
    }

    private LocalDateTime getFirstDayOfMonth(LocalDate time) {
        LocalDate firstDayOfMonth = time.withDayOfMonth(1);
        return LocalDateTime.of(firstDayOfMonth, time.atStartOfDay().toLocalTime());

    }

    private LocalDateTime getLastDayOfMonth(LocalDate time) {
        LocalDate lastDayOfMonth = time.withDayOfMonth(time.lengthOfMonth());
        return LocalDateTime.of(lastDayOfMonth, LocalTime.of(23,59,59,99));
    }

    @Override
    public Optional<Game> getBestGameForStaffUser(String staff_id, Long id) {
        return gameRepo.findFirstByStaff_idAndIdNotOrderByScoreDescCompletionTimeMsAsc(staff_id, id);
    }

    @Transactional
    public void update(Game game) {
        gameRepo.save(game);
    }

    @Override
    public int getBestRankForUser(String staff_id) {
        Staff staff = staffRepo.getOne(staff_id);
        List<GameScore> gameScores = this.getGameScore();

        int rank = gameScores.stream()
                .map(GameScore::getStaff)
                .collect(Collectors.toList())
                .indexOf(staff);
        rank++;

        return rank;
    }
}
