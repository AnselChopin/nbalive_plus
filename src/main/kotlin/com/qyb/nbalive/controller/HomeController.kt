package com.qyb.nbalive.controller

import com.alibaba.fastjson.JSONObject
import com.qyb.nbalive.service.NbaLiveService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *
 */
@Controller
@RequestMapping("/home")
class HomeController {
    @Autowired private lateinit var nbaLiveService: NbaLiveService

    /** 获取首页数据 **/
    @GetMapping
    fun getHomeData(model: Model,request: HttpServletRequest): String{
        model["nbaVideoList"] = nbaLiveService.getLiveList()
        model["systemNotice"] = nbaLiveService.getNewestSystemNotice()
//        model["systemNotice"] = JSONObject().apply { this["content"] = null }
                return "home_v02"
    }
}