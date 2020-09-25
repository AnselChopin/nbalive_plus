package com.qyb.nbalive.repository;

import com.qyb.nbalive.entity.LiveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LiveRequestReporitory extends JpaRepository<LiveRequest,Integer> {
    @Query(nativeQuery = true, value = "select * from t_live_request ORDER BY rqst_time DESC")
    List<LiveRequest> getAll();

    @Query(nativeQuery = true, value = "select * from t_live_request ORDER BY rqst_time DESC LIMIT ?1,?2")
    List<LiveRequest> getAll(int offset, int size);
}
