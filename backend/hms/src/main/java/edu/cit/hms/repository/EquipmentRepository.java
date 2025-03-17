package edu.cit.hms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import edu.cit.hms.entity.DepartmentEntity;
import edu.cit.hms.entity.EquipmentEntity;
import edu.cit.hms.entity.RoomEntity;
import edu.cit.hms.junctions.DepartmentEquipment;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, DepartmentEquipment> {
    public List<EquipmentEntity> findByEquipmentName(String equipmentName);
    public List<EquipmentEntity> findByEquipmentType(String equipmentType);
    public List<EquipmentEntity> findByStatus(String status);

    public Optional<EquipmentEntity> findByRoom(RoomEntity room);
    public List<EquipmentEntity> findByDepartments(DepartmentEntity department);
}
