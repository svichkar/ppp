package com.nixsolutions.spring.dao;

import java.util.List;

/**
 * Created by kozlovskij on 12/22/2015.
 */
interface GenericDAO<E> {

    void create(E entity);

    void update(E entity);

    void delete(E entity);

    E findByID(Long id);

    List<E> findAll();
}
