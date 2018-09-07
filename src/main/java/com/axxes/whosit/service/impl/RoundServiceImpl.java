package com.axxes.whosit.service.impl;

import com.axxes.whosit.domain.Round;
import com.axxes.whosit.repository.RoundRepository;
import com.axxes.whosit.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoundServiceImpl implements RoundService {
    private RoundRepository roundRepo;

    @Autowired
    public RoundServiceImpl(RoundRepository roundRepo) {
        this.roundRepo = roundRepo;
    }

    @Override
    public Optional<Round> findById(Long id) {
        return roundRepo.findById(id);
    }

    @Transactional
    public void update(Round round){
        roundRepo.save(round);
    }
}
