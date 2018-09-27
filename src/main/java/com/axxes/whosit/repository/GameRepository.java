package com.axxes.whosit.repository;

import com.axxes.whosit.domain.Game;
import com.axxes.whosit.domain.GameScore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    Optional<Game> findById(Long id);
    List<Game> findAll();
    Optional<Game> findFirstByStaff_idAndIdNotOrderByScoreDescCompletionTimeMsAsc(String staff_id, Long id);
    @Query(
            value = "select new com.axxes.whosit.domain.GameScore(g.staff.firstName, g.score, min(g.completionTimeMs) as completionTime, g.timestamp) " +
            "from Game g " +
                    "where (g.staff.id, g.score, g.timestamp) in (" +
                    "select game.staff.id, max(game.score) as score, g.timestamp " +
                    "from Game game " +
                    "GROUP BY game.staff.id, g.timestamp) " +
            "group by g.staff.firstName, g.score, g.timestamp " +
            "order by g.score desc, completionTime asc "
    )
    List<GameScore> getGameScores();
    List<Game> findDistinctStaff_idByOrderByScoreDescCompletionTimeMsAsc();
}
