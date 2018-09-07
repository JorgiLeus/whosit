package com.axxes.whosit.repository;

import com.axxes.whosit.domain.Round;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoundRepository extends CrudRepository<Round, Long> {
    Optional<Round> findById(Long id);
}
