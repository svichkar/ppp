package com.nixsolutions.servicestation.dao;

import java.util.List;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public interface JointDAO<E> {
    public boolean create (E entity);

    public boolean update(E entity);

    public boolean delete(E entity);

    public E findById(Integer id);

    public List<E> findAll();
}
