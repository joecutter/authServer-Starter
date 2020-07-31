package com.kisokolab.authserver.repo;

import com.kisokolab.authserver.entity.AppsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppsRepo extends JpaRepository<AppsEntity,Long> {
    Optional<AppsEntity> findByClientId(String clientId);
    boolean existsByClientId(String clientId);
}
