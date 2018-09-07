package com.axxes.whosit.service;

import com.axxes.whosit.domain.Round;

import java.util.Optional;

public interface RoundService {
    Optional<Round> findById(Long id);
    void update(Round round);
}
