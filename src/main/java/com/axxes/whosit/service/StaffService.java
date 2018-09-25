package com.axxes.whosit.service;

import com.axxes.whosit.domain.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<Staff> getAll();
    Optional<Staff> getStaffById(String id);
}
