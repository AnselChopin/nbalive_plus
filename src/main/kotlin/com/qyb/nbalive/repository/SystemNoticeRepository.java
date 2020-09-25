package com.qyb.nbalive.repository;

import com.qyb.nbalive.entity.SystemNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SystemNoticeRepository extends JpaRepository<SystemNotice,Integer> {
    @Query(nativeQuery = true, value = "select * from t_system_notice order by f_time desc limit 0,1")
    SystemNotice getNewestNotice();
}
