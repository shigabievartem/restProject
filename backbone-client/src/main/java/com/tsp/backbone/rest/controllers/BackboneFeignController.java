package com.tsp.backbone.rest.controllers;

import com.tsp.backbone.dto.BackboneDTO;
import com.tsp.backbone.rest.feign.client.BackboneFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api")
public class BackboneFeignController {
    private BackboneFeignClient client;


    @GetMapping("/get/{id}")
    public ResponseEntity<BackboneDTO> get(@PathVariable(value = "id") @NonNull Long id) {

        log.info("Получен запрос на получение сущности c id='{}'", id);

        return ResponseEntity.ok(client.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") @NonNull Long id) {

        log.info("Получен запрос на удаление сущности с id='{}'", id);

        client.delete(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/save")
    public ResponseEntity<BackboneDTO> save(@RequestBody @NonNull BackboneDTO dto) {
        log.info("Получен запрос на создание сущности [{}]", dto);

        return ResponseEntity.ok(client.save(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BackboneDTO> update(@PathVariable(value = "id") @NonNull Long id,
                                              @RequestBody @NonNull BackboneDTO dto) {

        log.info("Получен запрос на изменение сущности [{}] c id='{}'", dto, id);

        return ResponseEntity.ok(client.update(id, dto));
    }

    @Autowired
    public void setClient(BackboneFeignClient client) {
        this.client = client;
    }
}
