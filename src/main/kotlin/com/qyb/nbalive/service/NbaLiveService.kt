package com.qyb.nbalive.service

import com.alibaba.fastjson.JSONObject
import com.qyb.nbalive.cache.GlobalCache
import com.qyb.nbalive.common.HttpResult
import com.qyb.nbalive.entity.SystemNotice
import com.qyb.nbalive.entity.VideoSource
import com.qyb.nbalive.repository.LiveRequestReporitory
import com.qyb.nbalive.repository.SystemNoticeRepository
import com.qyb.nbalive.repository.VideoSourceRepository
import com.qyb.nbalive.util.MyHrefEncodeUtil
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *
 */
@Service
class NbaLiveService {
    @Autowired
    private lateinit var videoSourceRepository: VideoSourceRepository

    @Autowired
    private lateinit var globalCache: GlobalCache

    @Autowired
    private lateinit var systemNoticeRepository: SystemNoticeRepository

    // <editor-fold> ==================================== 外部调用 =====================================================
    /** 获取直播线路 */
    fun getLiveList(): List<VideoSource> {
        val sourceList = videoSourceRepository.all
        sourceList.forEach {
            it.videoHtml = "nba/list/" + MyHrefEncodeUtil.encode(it.videoHtml)
        }
        return sourceList
    }

    /** 获取节目列表，返回html格式文本 **/
    fun getLiveList(key: String): String {
        var result = ""
        val nbaListHtml = globalCache.getValue(MyHrefEncodeUtil.decode(key))
        if (!nbaListHtml.isNullOrEmpty()) {
            try {
                val doc = Jsoup.connect(nbaListHtml).get()
                val liveListEelemnt = doc.getElementById("JM_List")

                setStyleToTitle(liveListEelemnt, "a.s0");  //选择带s0样式的a标签
                setStyleToTitle(liveListEelemnt, "a.s1");  //选择带s1样式的a标签
                setStyleToTitle(liveListEelemnt, "a.s2");  //选择带s2样式的a标签

                val listTdL: Elements = liveListEelemnt.select("td.L") //选择带L样式属性的td标签
                for (a in 0 until listTdL.size) {
                    val listA: Elements = listTdL[a].select("a[target]")
                    for (b in 0 until listA.size) {
                        val e = listA[b]
                        val href = e.attr("href")
                        if (href.startsWith("https://www.wanqiuzhibo.com/channel/")) {
                            val encodeHref = MyHrefEncodeUtil.encode(href);
                            e.attr("href", "../video?vs=$encodeHref")
                            e.attr("style", "color:#FF5722")
                        } else {
                            e.attr("style", "display:none")
                        }
                    }
                }
                result = liveListEelemnt.html()
            } catch (e: Exception) {
                result = "<b style='color:white'>网页解析出错，建议刷新页面，若有问题请联系开发人员</b><br><b style='color:white'>微信：15183787353</b>"
            }
        }
        return result
    }

    /** 返回直播源页面 **/
    fun getLiveVideoPlayer(vs: String): String {
        var result = ""
        try {
            val doc = Jsoup.connect(MyHrefEncodeUtil.decode(vs)).get()
            val es1 = doc.select("script")
            for (i in 0 until es1.size) {
                val s = es1[i].html()
                if (s.startsWith("var url = \"")) {
                    result = s.replace("var url = ", "").replace("\"", "").replace(";", "")
                    break
                }
            }
            result = "https://www.wanqiuzhibo.com/e/DownSys/play/aliplayer.php?url=$result"
        } catch (e: Exception) {
            result = "<b style='color:white'>网页解析出错，建议刷新页面，若有问题请联系开发人员</b><br><b style='color:white'>微信：15183787353</b>"
        }

        return result
    }

    /** 获取最新系统通知 **/
    fun getNewestSystemNotice(): SystemNotice = systemNoticeRepository.newestNotice

    // </editor-fold> ==================================================================================================

    // <editor-fold> ==================================== 内部方法 =====================================================
    /** 处理标题样式 **/
    private fun setStyleToTitle(liveListEelemnt: Element, select: String) {
        try {
            val titleList = liveListEelemnt.select(select)
            titleList.forEach {
                it.attr("href", "javascript:;")
                it.attr("style", "text-decoration:none;")
            }
        } catch (e: Exception) {
        }
    }
    // </editor-fold> ==================================================================================================
}