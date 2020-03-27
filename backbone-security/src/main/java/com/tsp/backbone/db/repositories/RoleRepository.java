package com.tsp.backbone.db.repositories;

import com.tsp.backbone.db.entities.Role;
import com.tsp.backbone.rest.enums.RoleNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(RoleNames role);
}
