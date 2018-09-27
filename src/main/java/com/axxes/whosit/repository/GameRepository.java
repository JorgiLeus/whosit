package com.axxes.whosit.repository;

import com.axxes.whosit.domain.Game;
import com.axxes.whosit.domain.GameScore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    Optional<Game> findById(Long id);
    List<Game> findAll();
    Optional<Game> findFirstByStaff_idAndIdNotOrderByScoreDescCompletionTimeMsAsc(String staff_id, Long id);
    List<Game> findByCompletionTimeMsGreaterThanAndTimestampBetween(long completionTimeMs,LocalDateTime start, LocalDateTime end);
}
