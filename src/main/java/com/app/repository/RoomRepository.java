package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{

    List<Room> findByPg_Id(Long pgId);

}