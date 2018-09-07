package com.axxes.whosit.repository;

import com.axxes.whosit.domain.AxxesUser;
import com.axxes.whosit.domain.Game;
import com.axxes.whosit.domain.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
    Optional<Game> findById(Long id);
    List<Game> findAll();
    List<Game> findTop10ByOrderByScoreDescCompletionTimeMsAsc();
    Optional<Game> findFirstByStaff_idOrderByScoreDescCompletionTimeMsAsc(Long staff_id);
}
