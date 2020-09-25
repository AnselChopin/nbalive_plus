package com.qyb.nbalive.controller.admin

import com.qyb.nbalive.service.ManageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *      后台管理
 */
@Controller
@RequestMapping("/admin")
class ManageController {

    @Autowired
    private lateinit var manageService: ManageService

    /** 获取访问信息 **/
    @GetMapping("/request/log")
    fun getUserRequestLog(model: Model, @RequestParam("offset") offset:Int, @RequestParam("size") size:Int): String{
        model["data"] = manageService.getRequestLog(offset, size)
        return "admin_request_log"
    }

    /** 获取访问信息 **/
    @GetMapping("/home")
    fun getHome(model: Model): String{
        return "admin_home"
    }
}