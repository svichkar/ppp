package com.nixsolutions.spring.dao;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public interface GenericDAO<E> {

    public void create(E entity);

    public void update(E entity);

    public void delete(E entity);

    public E findByID(Long id);

    public List<E> findAll();
}
