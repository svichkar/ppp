package com.nixsolutions.studentgrade.service.impl;

import com.nixsolutions.studentgrade.dao.StatusDao;
import com.nixsolutions.studentgrade.model.Status;
import com.nixsolutions.studentgrade.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by svichkar on 1/28/2016.
 */
@Service
@Transactional
public class StatusServiceImpl implements StatusService {

    private StatusDao statusDao;

    @Autowired
    @Qualifier("statusDao")
    public void setStatusDao(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    @Override
    public void create(Status status) {

        statusDao.create(status);
    }

    @Override
    public void update(Status status) {

        statusDao.update(status);
    }

    @Override
    public void delete(Status status) {

        statusDao.delete(status);
    }

    @Override
    public List<Status> findAll() {

        return statusDao.findAll();
    }

    @Override
    public Status findById(Long id) {

        return statusDao.findById(id);
    }

    @Override
    public Status findByName(String status) {

        return statusDao.findByName(status);
    }
}
