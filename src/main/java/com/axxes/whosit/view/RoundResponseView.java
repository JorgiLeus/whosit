package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoundResponseView {
    @JsonProperty("answer")
    private Long staffId;

    public RoundResponseView(Long staffId) {
        this.staffId = staffId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}
