package com.tsp.backbone.db.repositories;

import com.tsp.backbone.db.entities.BackboneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackboneRepository extends JpaRepository<BackboneEntity, Long> {
}
