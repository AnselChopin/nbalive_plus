package com.qyb.nbalive.aop

import com.alibaba.fastjson.JSONObject
import com.qyb.nbalive.common.HttpResult
import org.springframework.web.HttpMediaTypeNotAcceptableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.multipart.MultipartException

@RestControllerAdvice
class ExceptionAdvisor {

    // 捕捉所有操作失败时抛出的自定义异常
    //@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "")//就是报错页面第一行的文字,在网页上看得到效果：HTTP Status 401 - reason
    @ExceptionHandler(MyFailException::class)
    fun handleMyFailException(e: MyFailException): JSONObject = HttpResult.fail(e.message ?: "MyFail Exception")

    //限制文件上传大小，上传时超过大小会抛出异常
    @ExceptionHandler(MultipartException::class)
    fun handleMultipartException(e: MultipartException): JSONObject = HttpResult.fail(e.cause?.message ?: "NullPointer Exception")

    //空指针异常
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException::class)
    fun handleNullPointerException(e: NullPointerException): JSONObject = HttpResult.fail(e.message ?: "NullPointer Exception")

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): JSONObject = HttpResult.fail(e.message ?: "Illegal Argument Exception")

    @ExceptionHandler(HttpMediaTypeNotAcceptableException::class)
    fun handleHttpMediaTypeNotAcceptableException(e: HttpMediaTypeNotAcceptableException): JSONObject = HttpResult.fail(e.message ?: "Http MediaType Not Acceptable Exception")

    @ExceptionHandler(Exception::class)
    fun globalException(e: Exception): JSONObject = HttpResult.fail(e.message ?: "Server Exception")
}
