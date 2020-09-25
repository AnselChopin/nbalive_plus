package com.qyb.nbalive.repository;

import com.qyb.nbalive.entity.VideoSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoSourceRepository extends JpaRepository<VideoSource,Integer> {

    @Query(nativeQuery = true, value = "select * from t_live_source")
    List<VideoSource> getAll();
}
