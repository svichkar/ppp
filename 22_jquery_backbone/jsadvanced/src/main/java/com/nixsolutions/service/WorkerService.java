package com.nixsolutions.service;

import java.util.List;

import com.nixsolutions.bean.WorkerBean;
import com.nixsolutions.hibernate.entity.Worker;

public interface WorkerService {

	List<Worker> getAllWorkers();

	List<WorkerBean> getAllWorkersAsBeans();

	Worker getWorkerById(int id);

	void deleteWorker(Worker worker);

	void createWorker(Worker worker);

	void updateWorker(Worker worker);
}
