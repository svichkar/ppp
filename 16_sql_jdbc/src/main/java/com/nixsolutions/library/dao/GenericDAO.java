package com.nixsolutions.library.dao;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public interface GenericDAO<E> {

    public boolean create (E entity);

    public boolean update(E entity);

    public boolean delete(E entity);

    public Integer getId(E entity);

    public E findByID(Integer id);

    public List<E> findAll();
}
