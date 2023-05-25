package com.sdi.statisticsservice.repository;

import com.sdi.statisticsservice.domain.timeseries.DataPoint;
import com.sdi.statisticsservice.domain.timeseries.DataPointId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DataPointRepository extends CrudRepository<DataPoint, DataPointId> {

    List<DataPoint> findByIdAccount(String account);
}
