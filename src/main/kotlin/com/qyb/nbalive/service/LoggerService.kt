package com.qyb.nbalive.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LoggerService {
    private val logger: Logger = LoggerFactory.getLogger(LoggerService::class.java)

    fun info(str: String) {
        logger.info(str)
    }
}