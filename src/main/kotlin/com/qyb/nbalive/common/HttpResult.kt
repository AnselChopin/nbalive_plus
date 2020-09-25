package com.qyb.nbalive.common

import com.alibaba.fastjson.JSONObject

/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *
 */

class HttpResult {
    companion object{
        fun success(data: Any? = null): JSONObject{
            return JSONObject().apply {
                this["code"] = 1
                this["msg"] = "ok"
                this["time"] = System.currentTimeMillis()
                this["data"] = data
            }
        }

        fun success(msg: String? = "ok",data: Any? = null): JSONObject{
            return JSONObject().apply {
                this["code"] = 1
                this["msg"] = msg
                this["time"] = System.currentTimeMillis()
                this["data"] = data
            }
        }

        fun fail(msg: String? = "fail",data: Any? = null): JSONObject{
            return JSONObject().apply {
                this["code"] = -1
                this["msg"] = msg
                this["time"] = System.currentTimeMillis()
                this["data"] = data
            }
        }
    }
}