package com.nixsolutions.library.dao;

import com.nixsolutions.library.entity.User;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
public interface GenericDAO<E> {

    public E create (E entity);

    public void update(E entity);

    public void delete(E entity);

    public E findByID(Integer id);

    public List<E> findAll();

    E findByLogin(String login);
}
