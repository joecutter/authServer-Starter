package com.kisokolab.authserver.repo;

import com.kisokolab.authserver.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<UsersEntity,Long> {
    Optional<UsersEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
