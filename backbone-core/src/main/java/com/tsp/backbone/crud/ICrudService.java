package com.tsp.backbone.crud;

/**
 *
 * @param <D> - Тип сущности запроса (DTO)
 * @param <I> - Тип уникального идентификатора
 */
public interface ICrudService<D, I> {
    D get(I id);

    void deleteById(I id);

    void delete(D dto);

    D save(D dto);
}
