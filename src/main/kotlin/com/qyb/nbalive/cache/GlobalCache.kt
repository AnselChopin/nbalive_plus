package com.qyb.nbalive.cache

import org.springframework.stereotype.Component


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *
 */
@Component
class GlobalCache {
    //key: 网站首页     value: 该网站NBA直播对应链接
    private val NBA_LIVE_SOURCE_MAP = HashMap<String,String>()

    fun save(key: String,value: String){
        if(isContainKey(key)){
            NBA_LIVE_SOURCE_MAP.replace(key,value)
        }else{
            NBA_LIVE_SOURCE_MAP[key] = value
        }
    }

    fun remove(key: String){
        NBA_LIVE_SOURCE_MAP.remove(key)
    }

    fun getValue(key: String): String? = NBA_LIVE_SOURCE_MAP[key]

    fun isContainKey(key: String): Boolean = NBA_LIVE_SOURCE_MAP.containsKey(key)
}
