package com.nixsolutions.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.bean.WorkerBean;
import com.nixsolutions.dao.WorkerDAO;
import com.nixsolutions.hibernate.entity.Status;
import com.nixsolutions.hibernate.entity.Worker;
import com.nixsolutions.hibernate.entity.WorkerSpecialization;
import com.nixsolutions.service.WorkerService;

@Service
public class WorkerServiceImpl implements WorkerService {

	@Autowired
	private WorkerDAO workerDao;

	public List<Worker> getAllWorkers() {
		return workerDao.getAll();
	}

	public List<WorkerBean> getAllWorkersAsBeans() {
		return processAsBeans(getAllWorkers());
	}

	private List<WorkerBean> processAsBeans(List<Worker> workerList) {
		List<WorkerBean> resultList = new ArrayList<>();
		for (Worker item : workerList) {
			WorkerBean wb = new WorkerBean();
			wb.setWorkerId(item.getWorkerId());
			wb.setFirstName(item.getFirstName());
			wb.setLastName(item.getLastName());
			Status s = item.getStatus();
			wb.setStatus(s.getStatusName());
			WorkerSpecialization ws = item.getWorkerSpecialization();
			wb.setWorkerSpecialization(ws.getSpecializationName());
			resultList.add(wb);
		}
		return resultList;
	}

	public Worker getWorkerById(int id) {
		return workerDao.getByPK(id);
	}

	public void deleteWorker(Worker worker) {
		workerDao.delete(worker);
	}

	public void createWorker(Worker worker) {
		workerDao.createFrom(worker);
	}

	public void updateWorker(Worker worker) {
		workerDao.update(worker);
	}
}
