package com.sbt.backbone.rest.controllers;

import com.sbt.backbone.crud.ICrudService;
import com.sbt.backbone.dto.BackboneDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class BackboneController {

    private ICrudService<BackboneDTO, Long> crudService;

    @GetMapping("/get/{id}")
    public BackboneDTO get(@PathVariable(value = "id") @NonNull Long id) {
        log.info("Получен запрос на получение сущности c id='{}'", id);

        return crudService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") @NonNull Long id) {
        log.info("Получен запрос на удаление сущности с id='{}'", id);

        crudService.deleteById(id);
    }

    @PostMapping("/save")
    public BackboneDTO save(@RequestBody @NonNull BackboneDTO dto) {
        log.info("Получен запрос на создание сущности [{}]", dto);

        return crudService.save(dto);
    }

    @PutMapping("/update/{id}")
    public BackboneDTO update(@PathVariable(value = "id") @NonNull Long id, @RequestBody @NonNull BackboneDTO dto) {
        log.info("Получен запрос на изменение сущности [{}] c id='{}'", dto, id);

        dto.setId(id);

        return crudService.save(dto);
    }

    @Autowired
    public void setCrudService(ICrudService<BackboneDTO, Long> crudService) {
        this.crudService = crudService;
    }
}
