package com.qyb.nbalive.entity

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *      系统通知
 *      内容，时间
 */
@Entity
@Table(name = "t_system_notice")
data class SystemNotice (
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        var id: Int? = 0,

        @Column(name = "f_content")
        var content: String = "",

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        @Column(name = "f_time")
        var time: Date? = null,

        @Column(name = "f_state")
        var state: Int = 1
        )