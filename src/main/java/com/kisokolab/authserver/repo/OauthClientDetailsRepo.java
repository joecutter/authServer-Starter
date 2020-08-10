package com.kisokolab.authserver.repo;

import com.kisokolab.authserver.entity.OauthClientDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OauthClientDetailsRepo extends JpaRepository<OauthClientDetailsEntity,Long> {
    Optional<OauthClientDetailsEntity> findByClientId(String clientId);
    boolean existsByClientId(String clientId);
}
