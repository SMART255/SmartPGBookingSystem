package com.app.dto.response;

import lombok.Data;

@Data
public class RoomResponse {

    private Long id;

    private String roomNumber;

    private int capacity;

    private int availableBeds;

    private double rent;

    private boolean attachedBathroom;

    private boolean acAvailable;

    private String pgName;
}