package edu.cit.hms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import edu.cit.hms.entity.DepartmentEntity;
import edu.cit.hms.entity.EquipmentEntity;
import edu.cit.hms.entity.RoomEntity;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Integer> {
    public List<EquipmentEntity> findByEquipmentName(String equipmentName);
    public List<EquipmentEntity> findByEquipmentType(String equipmentType);
    public List<EquipmentEntity> findByStatus(String status);

    public Optional<EquipmentEntity> findByRoom(RoomEntity room);
    public List<EquipmentEntity> findByDepartments(DepartmentEntity department);
}
