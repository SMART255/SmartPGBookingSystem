package com.app.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.dto.request.AddPGRequest;
import com.app.dto.response.PGResponse;
import com.app.entity.Owner;
import com.app.entity.PG;
import com.app.enums.GenderAllowed;
import com.app.enums.Status;
import com.app.repository.OwnerRepository;
import com.app.repository.PGRepository;
import com.app.service.PGService;

@Service
public class PGServiceImpl implements PGService {

    private final PGRepository pgRepository;
    private final OwnerRepository ownerRepository;

    public PGServiceImpl(PGRepository pgRepository,
                         OwnerRepository ownerRepository) {
        this.pgRepository = pgRepository;
        this.ownerRepository = ownerRepository;
    }

    // =========================
    // ADD PG
    // =========================

    @Override
    public PGResponse addPG(AddPGRequest request) {

        Owner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        PG pg = new PG();

        pg.setPgName(request.getPgName());
        pg.setOwnerName(owner.getFirstName() + " " + owner.getLastName());
        pg.setAddress(request.getAddress());
        pg.setCity(request.getCity());
        pg.setState(request.getState());
        pg.setPincode(request.getPincode());
        pg.setGenderAllowed(request.getGenderAllowed());
        pg.setTotalRooms(request.getTotalRooms());
        pg.setAvailableRooms(request.getAvailableRooms());
        pg.setRentPerMonth(request.getRentPerMonth());
        pg.setDescription(request.getDescription());

        pg.setStatus(Status.ACTIVE);

        pg.setCreatedAt(LocalDateTime.now());
        pg.setUpdatedAt(LocalDateTime.now());

        pg.setOwner(owner);

        PG savedPG = pgRepository.save(pg);

        return mapToResponse(savedPG);
    }

    // =========================
    // GET PG BY ID
    // =========================

    @Override
    public PGResponse getPGById(Long id) {

        PG pg = pgRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PG not found"));

        return mapToResponse(pg);
    }

    // =========================
    // GET ALL PGs
    // =========================

    @Override
    public List<PGResponse> getAllPGs() {

        return pgRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // =========================
    // GET PGs BY OWNER
    // =========================

    @Override
    public List<PGResponse> getPGsByOwner(Long ownerId) {

        return pgRepository.findByOwner_Id(ownerId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // =========================
    // UPDATE PG
    // =========================

    @Override
    public PGResponse updatePG(Long id, AddPGRequest request) {

        PG pg = pgRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PG not found"));

        Owner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        pg.setPgName(request.getPgName());
        pg.setOwnerName(owner.getFirstName() + " " + owner.getLastName());
        pg.setAddress(request.getAddress());
        pg.setCity(request.getCity());
        pg.setState(request.getState());
        pg.setPincode(request.getPincode());
        pg.setGenderAllowed(request.getGenderAllowed());
        pg.setTotalRooms(request.getTotalRooms());
        pg.setAvailableRooms(request.getAvailableRooms());
        pg.setRentPerMonth(request.getRentPerMonth());
        pg.setDescription(request.getDescription());

        pg.setOwner(owner);
        pg.setUpdatedAt(LocalDateTime.now());

        PG updatedPG = pgRepository.save(pg);

        return mapToResponse(updatedPG);
    }

    // =========================
    // DELETE PG
    // =========================

    @Override
    public void deletePG(Long id) {

        PG pg = pgRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PG not found"));

        pgRepository.delete(pg);
    }
    
    
    @Override
    public List<PGResponse> searchByCity(String city) {

        return pgRepository.findByCityIgnoreCase(city)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    
    
    @Override
    public List<PGResponse> searchByGender(GenderAllowed gender) {

        return pgRepository.findByGenderAllowed(gender)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    
    @Override
    public List<PGResponse> searchByRent(Double rent) {

        return pgRepository.findByRentPerMonthLessThanEqual(rent)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    
    
    @Override
    public List<PGResponse> searchByName(String name) {

        return pgRepository.findByPgNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    
    @Override
    public List<PGResponse> availablePGs() {

        return pgRepository.findByAvailableRoomsGreaterThan(0)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    // =========================
    // COMMON RESPONSE METHOD
    // =========================

    private PGResponse mapToResponse(PG pg) {

        PGResponse response = new PGResponse();

        response.setId(pg.getId());
        response.setPgName(pg.getPgName());
        response.setOwnerName(pg.getOwnerName());
        response.setAddress(pg.getAddress());
        response.setCity(pg.getCity());
        response.setState(pg.getState());
        response.setPincode(pg.getPincode());
        response.setGenderAllowed(pg.getGenderAllowed());
        response.setTotalRooms(pg.getTotalRooms());
        response.setAvailableRooms(pg.getAvailableRooms());
        response.setRentPerMonth(pg.getRentPerMonth());
        response.setDescription(pg.getDescription());

        return response;
    }
}