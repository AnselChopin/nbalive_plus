package com.qyb.nbalive.service

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.qyb.nbalive.common.HttpResult
import com.qyb.nbalive.entity.LiveRequest
import com.qyb.nbalive.repository.LiveRequestReporitory
import com.qyb.nbalive.util.IpUtill
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *
 */
@Service
class UploadService {

    @Autowired private lateinit var liveRequestReporitory: LiveRequestReporitory

    fun uploadRequestLog(request: HttpServletRequest, param: String): JSONObject {
        if(param.isNotEmpty()){
            val obj = JSON.parseObject(param)
            val lr = LiveRequest(
                  null,IpUtill.getIPAddress(request),null,
                    obj.getString("device_model"),obj.getString("device_os"),obj.getString("user_agent"),
                    obj.getString("version_webkit"),obj.getString("version_build")
            )
            liveRequestReporitory.save(lr)
        }
        return HttpResult.success()
    }
}