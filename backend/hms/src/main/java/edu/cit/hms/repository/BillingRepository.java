package edu.cit.hms.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cit.hms.entity.*;

@Repository
public interface BillingRepository extends JpaRepository <BillingEntity, Integer> {
    public List<BillingEntity> findByIssuedAt(Date issuedAt);
    public List<BillingEntity> findByAmount(double amount);

    public Optional<BillingEntity> findByRoom(RoomEntity room);
    public Optional<BillingEntity> findByPatient(PatientEntity patient);
}
