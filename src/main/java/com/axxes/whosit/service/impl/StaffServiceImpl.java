package com.axxes.whosit.service.impl;

import com.axxes.whosit.domain.Staff;
import com.axxes.whosit.repository.StaffRepository;
import com.axxes.whosit.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    private StaffRepository staffRepo;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepo){
        this.staffRepo = staffRepo;
    }

    public List<Staff> getAll(){
        return staffRepo.findAll();
    }

    @Override
    public Optional<Staff> getStaffById(String id) {
        return staffRepo.findById(id);
    }


}
