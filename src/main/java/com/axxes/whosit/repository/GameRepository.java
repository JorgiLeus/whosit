package com.axxes.whosit.repository;

import com.axxes.whosit.domain.AxxesUser;
import com.axxes.whosit.domain.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    Optional<Game> findById(Long id);
    List<Game> findAll();
    List<Game> findTop10ByOrderByScoreAndCompletionTimeMs();
    Game findFirstByAxxes_User_IdOrderByScoreAndCompletionTimeMs(Long axxes_user_id);
}
