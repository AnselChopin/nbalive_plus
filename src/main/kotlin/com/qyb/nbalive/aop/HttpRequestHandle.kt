package com.qyb.nbalive.aop


import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

/**
 * 请求日志统一处理
 */
@Component
@Aspect
class HttpRequestHandle {
    companion object {
        private val logger = LoggerFactory.getLogger(HttpRequestHandle::class.java)
    }

    @Value("\${my-config.enable-http-aop}")
    private var enableHttpAop: Boolean = false

    @Pointcut(value = "execution(* com.qyb.nbalive.controller.*.*(..))")
    fun expression() {
    }

    @Before(value = "expression()")
    fun before(point: JoinPoint) {
        if(!enableHttpAop) return
        logger.info("\n")
        logger.info("----------------------- http request -----------------------")
        val attributes: ServletRequestAttributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val request = attributes.request
        logger.info("url={}", "${request.remoteHost}:${request.serverPort}${request.requestURI}")
        logger.info("method: ${request.method}")
        logger.info("ip={}", request.remoteAddr)
        val className = point.signature.declaringTypeName
        val methodName = point.signature.name
        logger.info("class.method={}", "$className.$methodName()")

        //headers
//        val headerNames = request.headerNames
//        while(headerNames.hasMoreElements()){
//            val headerKey: String = headerNames.nextElement()
//            logger.info("$headerKey : ${request.getHeader(headerKey)}")
//        }

        logger.info("params:")
        point.args.forEach { logger.info(it.toString()) }
    }


    @AfterReturning(value = "expression()", returning = "object")
    fun afterReturning(`object`: Any) {
        if(!enableHttpAop) return
        logger.info("----------------------- http response -----------------------")
        logger.info(`object`.toString())
    }

    @AfterThrowing(value = "expression()", throwing = "ex")
    fun afterThrowing(joinPoint: JoinPoint, ex: Throwable) {
        if(!enableHttpAop) return
        logger.info("----------------------- http throwing -----------------------")
        //目标方法名：
        logger.info("exception: ${ex.javaClass.simpleName}")
        logger.info("message: ${ex.message}")
        val className = joinPoint.signature.declaringTypeName
        val methodName = joinPoint.signature.name
        logger.info("class.method={}", "$className.$methodName()")
    }
}
