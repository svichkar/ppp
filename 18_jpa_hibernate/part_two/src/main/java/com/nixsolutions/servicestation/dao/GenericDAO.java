package com.nixsolutions.servicestation.dao;

import java.util.Set;

/**
 * Created by rybkinrolla on 20.01.2016.
 */
public interface GenericDAO<E> {
    public void create(E entity);

    public void update(E entity);

    public void delete(E entity);

    public E findById(Long id);

    public Set<E> findAll();
}
