package com.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.dto.request.AddRoomRequest;
import com.app.dto.response.RoomResponse;
import com.app.entity.PG;
import com.app.entity.Room;
import com.app.repository.PGRepository;
import com.app.repository.RoomRepository;
import com.app.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final PGRepository pgRepository;

    public RoomServiceImpl(RoomRepository roomRepository,
                           PGRepository pgRepository) {
        this.roomRepository = roomRepository;
        this.pgRepository = pgRepository;
    }

    @Override
    public RoomResponse addRoom(AddRoomRequest request) {

        PG pg = pgRepository.findById(request.getPgId())
                .orElseThrow(() -> new RuntimeException("PG Not Found"));

        Room room = new Room();

        room.setRoomNumber(request.getRoomNumber());
        room.setCapacity(request.getCapacity());
        room.setAvailableBeds(request.getAvailableBeds());
        room.setRent(request.getRent());
        room.setAttachedBathroom(request.isAttachedBathroom());
        room.setAcAvailable(request.isAcAvailable());
        room.setCreatedAt(LocalDateTime.now());
        room.setUpdatedAt(LocalDateTime.now());

        room.setPg(pg);
        
        System.out.println("PG ID: " + request.getPgId());
        System.out.println("PG Name: " + pg.getPgName());

        Room savedRoom = roomRepository.save(room);

        RoomResponse response = new RoomResponse();

        response.setId(savedRoom.getId());
        response.setRoomNumber(savedRoom.getRoomNumber());
        response.setCapacity(savedRoom.getCapacity());
        response.setAvailableBeds(savedRoom.getAvailableBeds());
        response.setRent(savedRoom.getRent());
        response.setAttachedBathroom(savedRoom.isAttachedBathroom());
        response.setAcAvailable(savedRoom.isAcAvailable());
        response.setPgName(savedRoom.getPg().getPgName());

        return response;
    }
    
    private RoomResponse mapToResponse(Room room) {

        RoomResponse response = new RoomResponse();

        response.setId(room.getId());
        response.setRoomNumber(room.getRoomNumber());
        response.setCapacity(room.getCapacity());
        response.setAvailableBeds(room.getAvailableBeds());
        response.setRent(room.getRent());
        response.setAttachedBathroom(room.isAttachedBathroom());
        response.setAcAvailable(room.isAcAvailable());

        if (room.getPg() != null) {
            response.setPgName(room.getPg().getPgName());
        }

        return response;
    }
    @Override
    public RoomResponse getRoomById(Long id){

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        return mapToResponse(room);
    }
    @Override
    public List<RoomResponse> getAllRooms(){

        return roomRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public List<RoomResponse> getRoomsByPG(Long pgId) {

        return roomRepository.findByPg_Id(pgId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }    @Override
    public RoomResponse updateRoom(Long id,
                                   AddRoomRequest request){

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        PG pg = pgRepository.findById(request.getPgId())
                .orElseThrow(() -> new RuntimeException("PG not found"));

        room.setRoomNumber(request.getRoomNumber());
        room.setCapacity(request.getCapacity());
        room.setAvailableBeds(request.getAvailableBeds());
        room.setRent(request.getRent());
        room.setAttachedBathroom(request.isAttachedBathroom());
        room.setAcAvailable(request.isAcAvailable());

        room.setPg(pg);

        Room updated = roomRepository.save(room);

        return mapToResponse(updated);
    }

    @Override
    public void deleteRoom(Long id){

        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        roomRepository.delete(room);
    }
}