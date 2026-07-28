package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.request.AddRoomRequest;
import com.app.dto.response.RoomResponse;
import com.app.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/add")
    public ResponseEntity<RoomResponse> addRoom(@RequestBody AddRoomRequest request) {

        System.out.println("========== REQUEST ==========");
        System.out.println("Room Number: " + request.getRoomNumber());
        System.out.println("PG ID: " + request.getPgId());

        RoomResponse response = roomService.addRoom(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(
            @PathVariable Long id){

        return ResponseEntity.ok(roomService.getRoomById(id));
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<RoomResponse>> getAllRooms(){

        return ResponseEntity.ok(roomService.getAllRooms());
    }
    
    @GetMapping("/pg/{pgId}")
    public ResponseEntity<List<RoomResponse>> getRoomsByPG(
            @PathVariable Long pgId){

        return ResponseEntity.ok(roomService.getRoomsByPG(pgId));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable Long id,
            @RequestBody AddRoomRequest request){

        return ResponseEntity.ok(roomService.updateRoom(id, request));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoom(
            @PathVariable Long id){

        roomService.deleteRoom(id);

        return ResponseEntity.ok("Room Deleted Successfully");
    }
    
}