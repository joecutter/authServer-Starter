package com.kisokolab.authserver.repo;

import com.kisokolab.authserver.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo extends JpaRepository<PermissionEntity,Integer> {
}
