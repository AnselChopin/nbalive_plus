package com.qyb.nbalive.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *      请求设备统计
 */
@Entity
@Table(name = "t_live_request")
data class LiveRequest(
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        var id: Int? = 0,

        @Column(name = "rqst_ip")
        var requestIP: String? = "",

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @Column(name = "rqst_time")
        var requestTime: Date? = null,

        @Column(name = "device_model")
        var deviceModel: String? = "",

        @Column(name = "device_os")
        var deviceOs: String? = "",

        @Column(name = "user_agent")
        var userAgent: String? = "",

        @Column(name = "version_webkit")
        var versionWebkit: String? = "",

        @Column(name = "version_build")
        var versionBuild: String? = ""
)