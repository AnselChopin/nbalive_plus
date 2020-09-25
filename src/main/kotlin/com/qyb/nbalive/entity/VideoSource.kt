package com.qyb.nbalive.entity

import javax.persistence.*


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *      网上直播源页面
 * tips：
 *      data class 除非全部提供默认值，否则是没有空构造函数的
 */
@Entity
@Table(name = "t_live_source")
data class VideoSource(
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        var id: Int = 0,

        @Column(name = "video_source")
        var videoSource: String = "",

        @Column(name = "video_html")
        var videoHtml: String = "",

        @Column(name = "info")
        var info: String = ""
)