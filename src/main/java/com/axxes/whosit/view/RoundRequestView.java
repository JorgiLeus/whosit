package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoundRequestView {
    @JsonProperty("pictureId")
    private String staffId;

    public RoundRequestView() {
    }

    public RoundRequestView(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

}
