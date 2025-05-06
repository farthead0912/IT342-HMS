package edu.cit.hms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import edu.cit.hms.entity.UserEntity;
import edu.cit.hms.enums.Roles;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public Optional<UserEntity> findByUsername(String username);
    public Optional<UserEntity> findByEmail(String email);
    public Optional<UserEntity> findByRole(Roles role);
}
