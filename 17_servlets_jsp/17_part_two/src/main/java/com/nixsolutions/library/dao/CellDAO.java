package com.nixsolutions.library.dao;

import com.nixsolutions.library.entity.Cell;

/**
 * Created by kozlovskij on 12/23/2015.
 */
public interface CellDAO extends GenericDAO<Cell> {
    Cell findByName (String name);
}
