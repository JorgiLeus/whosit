package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoundRequestView {
    @JsonProperty("pictureId")
    private Long staffId;

    public RoundRequestView() {
    }

    public RoundRequestView(Long staffId) {
        this.staffId = staffId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

}
