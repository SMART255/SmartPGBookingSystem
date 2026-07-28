package com.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AddRoomRequest {

    @NotBlank
    private String roomNumber;

    @Positive
    private int capacity;

    @Positive
    private int availableBeds;

    @Positive
    private double rent;

    private boolean attachedBathroom;

    private boolean acAvailable;

    @NotNull
    private Long pgId;
    
    
    public Long getPgId() {
        return pgId;
    }

    public void setPgId(Long pgId) {
        this.pgId = pgId;
    }

}