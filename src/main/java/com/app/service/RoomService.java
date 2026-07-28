package com.app.service;

import java.util.List;

import com.app.dto.request.AddRoomRequest;
import com.app.dto.response.RoomResponse;

public interface RoomService {

	RoomResponse addRoom(AddRoomRequest request);

	RoomResponse getRoomById(Long id);

	List<RoomResponse> getAllRooms();

	List<RoomResponse> getRoomsByPG(Long pgId);

	RoomResponse updateRoom(Long id, AddRoomRequest request);

	void deleteRoom(Long id);
}