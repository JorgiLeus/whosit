package com.axxes.whosit.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoundResponseView {
    @JsonProperty("correctPictureId")
    private String staffId;


    public RoundResponseView(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
