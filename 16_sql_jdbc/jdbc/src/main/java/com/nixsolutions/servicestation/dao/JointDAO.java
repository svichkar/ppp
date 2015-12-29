package com.nixsolutions.servicestation.dao;

/**
 * Created by rybkinrolla on 29.12.2015.
 */
public interface JointDAO<E> {
    public void create (E entity);

    public void update(E entity);

    public void delete(E entity);

    public E findById(Integer id);
}
