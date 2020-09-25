package com.qyb.nbalive.controller

import com.alibaba.fastjson.JSONObject
import com.qyb.nbalive.common.HttpResult
import com.qyb.nbalive.service.NbaLiveService
import com.qyb.nbalive.util.MyHrefEncodeUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *
 */

@Controller
@RequestMapping("/nba")
class NbaLiveController {

    @Autowired private lateinit var nbaLiveService: NbaLiveService

    /** 获取节目列表，返回html格式文本 **/
    @GetMapping("/list/{key}")
    fun getLiveList(model: Model,@PathVariable("key") key: String): String{
        model["liveList"] = nbaLiveService.getLiveList(key)
        return "nba_list"
    }

    /** 返回直播源页面 **/
    @GetMapping("/video")
    fun getVideoPlayer(model: Model,@RequestParam("vs") vs: String): String{
        model["videoPlayer"] = nbaLiveService.getLiveVideoPlayer(vs)
        return "nba_video_player"
    }
    
    // <editor-fold> ====================================  =====================================================
    
    // </editor-fold> ==================================================================================================
}