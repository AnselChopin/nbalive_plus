package com.qyb.nbalive.service

import com.alibaba.fastjson.JSONObject
import com.qyb.nbalive.common.HttpResult
import com.qyb.nbalive.repository.LiveRequestReporitory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *
 */
@Service
class ManageService {
    @Autowired
    private lateinit var liveRequestReporitory: LiveRequestReporitory

    /** 获取访问信息 **/
    fun getRequestLog(offset: Int, size: Int): JSONObject {
        val list = liveRequestReporitory.getAll(offset, size)
        val count = liveRequestReporitory.all.size
        return HttpResult.success("$count", list)
    }
}