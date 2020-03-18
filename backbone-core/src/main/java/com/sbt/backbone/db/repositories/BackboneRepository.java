package com.sbt.backbone.db.repositories;

import com.sbt.backbone.db.entities.BackboneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackboneRepository extends JpaRepository<BackboneEntity, Long> {
}
