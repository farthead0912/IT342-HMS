package edu.cit.hms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.entity.RoomEntity;
import edu.cit.hms.entity.StaffEntity;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Integer> {
    public List<RoomEntity> findByRoomType(String roomType);
    public Optional<RoomEntity> findByRoomNumber(int roomNumber);
    public List<RoomEntity> findByFloorNumber(int floorNumber);
    public List<RoomEntity> findByIsOccupied(boolean isOccupied);
    public Optional<RoomEntity> findByPatient(PatientEntity patient);
    public Optional<RoomEntity> findByStaff(StaffEntity staff);
    public List<RoomEntity> findByRoomPrice(double roomPrice);
}
