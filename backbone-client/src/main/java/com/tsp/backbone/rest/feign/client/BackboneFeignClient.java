package com.tsp.backbone.rest.feign.client;

import com.tsp.backbone.dto.BackboneDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@FeignClient("backbone-db-app")
public interface BackboneFeignClient {

    @GetMapping("api/get/{id}")
    BackboneDTO get(@PathVariable(value = "id") @NonNull Long id);

    @DeleteMapping("api/delete/{id}")
    void delete(@PathVariable(value = "id") @NonNull Long id);

    @PostMapping("api/save")
    BackboneDTO save(@RequestBody @NonNull BackboneDTO dto);

    @PutMapping("/api/update/{id}")
    BackboneDTO update(@PathVariable(value = "id") @NonNull Long id, @RequestBody @NonNull BackboneDTO dto);

}
