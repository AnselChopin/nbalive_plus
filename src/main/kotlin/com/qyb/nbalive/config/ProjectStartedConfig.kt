package com.qyb.nbalive.config

import com.qyb.nbalive.cache.GlobalCache
import com.qyb.nbalive.repository.VideoSourceRepository
import com.qyb.nbalive.service.LoggerService
import org.jsoup.Jsoup
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import java.lang.Exception


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *
 */

@Configuration
class ProjectStartedConfig : CommandLineRunner {

    @Autowired private lateinit var loggerService: LoggerService
    @Autowired private lateinit var videoSourceRepository: VideoSourceRepository
    @Autowired private lateinit var globalCache: GlobalCache

    override fun run(vararg args: String?) {
        initNbaLiveSource()
        logStartFinished()
    }

    /** 初始化nba直播页面地址，保存到内存中 **/
    private fun initNbaLiveSource() {
        val sourceList = videoSourceRepository.all
        sourceList.forEach {
            val key = it.videoHtml
            if (!globalCache.isContainKey(key) && key.isNotEmpty()) {
                Thread(Runnable {
                    var nbaLiveLink = ""
                    try {
                        val doc = Jsoup.connect(key).get()
                        val elements = doc.select("a[target$=_self]")
                        for(i in 0 until elements.size){
                            val e = elements[i]
                            if(e.text() == "NBA"){
                                nbaLiveLink = e.attr("href")
                                break
                            }
                        }
                    } catch (e: Exception) {}
                    if(nbaLiveLink.isNotEmpty()){
                        globalCache.save(key,nbaLiveLink)
                        loggerService.info("globalCache ：{ $key : $nbaLiveLink }");
                    }
                }).start()
            }
        }
    }

    /** 打印项目启动完成 **/
    private fun logStartFinished() {
        loggerService.info("-------------------------------------------------------------------");
        loggerService.info("--------------------------   启动完成    --------------------------");
        loggerService.info("-------------------------------------------------------------------");
    }
}