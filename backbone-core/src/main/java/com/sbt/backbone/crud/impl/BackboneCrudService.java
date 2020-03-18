package com.sbt.backbone.crud.impl;

import com.sbt.backbone.crud.ICrudService;
import com.sbt.backbone.db.entities.BackboneEntity;
import com.sbt.backbone.dto.BackboneDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Supplier;

@Slf4j
@Service
public class BackboneCrudService implements ICrudService<BackboneDTO, Long> {
    private JpaRepository<BackboneEntity, Long> repository;
    private ModelMapper modelMapper;

    @Override
    public BackboneDTO get(Long id) {
        return processAction(() -> repository.findById(id).orElse(null));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(BackboneDTO dto) {
        repository.delete(Objects.requireNonNull(modelMapper.map(dto, BackboneEntity.class)));
    }

    @Override
    public BackboneDTO save(BackboneDTO dto) {
        return processAction(() -> repository.save(Objects.requireNonNull(modelMapper.map(dto, BackboneEntity.class))));
    }

    private BackboneDTO processAction(Supplier<BackboneEntity> action) {
        try {
            return modelMapper.map(action.get(), BackboneDTO.class);
        } catch (Exception ex) {
            log.warn("Ошибка при выполнении сервиса.", ex);
            return null;
        }
    }

    @Autowired
    public void setRepository(JpaRepository<BackboneEntity, Long> repository) {
        this.repository = repository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
