package com.nixsolutions.servicestation.dao;

import java.util.Set;

/**
 * Created by rybkinrolla on 20.01.2016.
 */
public interface GenericDAO<E> {
    void create(E entity);

    void update(E entity);

    void delete(E entity);

    E findById(Long id);

    Set<E> findAll();
}
