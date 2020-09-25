package com.qyb.nbalive.controller

import com.alibaba.fastjson.JSONObject
import com.qyb.nbalive.service.UploadService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *
 */
@Controller
@RequestMapping("/upload")
class UploadController {

    @Autowired private lateinit var uploadService: UploadService

    /** 记录请求的设备信息 **/
    @PostMapping("/request/log")
    @ResponseBody
    fun uploadRequestLog(request: HttpServletRequest, @RequestBody param: String): JSONObject = uploadService.uploadRequestLog(request,param)

}