package com.qyb.nbalive.controller

import com.alibaba.fastjson.JSONObject
import com.qyb.nbalive.common.HttpResult
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.random.Random


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *
 */
@Controller
@RequestMapping("/test")
class TestController {

    @GetMapping("/t1")
    @ResponseBody
    fun getTime(): JSONObject{
        return if(Random.nextBoolean()){
            HttpResult.success(Date().toLocaleString())
        }else{
            HttpResult.fail("emm")
        }
    }
}