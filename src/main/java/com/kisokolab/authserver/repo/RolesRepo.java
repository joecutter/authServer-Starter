package com.kisokolab.authserver.repo;

import com.kisokolab.authserver.entity.ERole;
import com.kisokolab.authserver.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepo extends JpaRepository<RolesEntity,Long> {
    Optional<RolesEntity> findByName(ERole role);
}
