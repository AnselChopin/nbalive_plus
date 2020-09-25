package com.qyb.nbalive.util

import java.lang.Exception
import java.net.InetAddress
import java.net.UnknownHostException
import javax.servlet.http.HttpServletRequest
import kotlin.random.Random


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *  获取请求的IP地址
 */

object IpUtill {
    private const val UNKNOWN = "unknown"
    private const val LOCALHOST = "127.0.0.1"
    private const val SEPARATOR = ","

    fun getIPAddress(request: HttpServletRequest): String {
        var ipAddress: String?
        try {
            ipAddress = request.getHeader("x-forwarded-for")
            if (ipAddress.isNullOrEmpty() || UNKNOWN.equals(ipAddress, true)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress.isNullOrEmpty() || UNKNOWN.equals(ipAddress, true)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress.isNullOrEmpty() || UNKNOWN.equals(ipAddress, true)) {
                ipAddress = request.remoteAddr
                if (LOCALHOST == ipAddress) {
                    var inet: InetAddress? = null
                    try {
                        inet = InetAddress.getLocalHost()
                    } catch (e: UnknownHostException) {
                        e.printStackTrace()
                    }
                    ipAddress = inet!!.hostAddress
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***".length()
            if (ipAddress != null && ipAddress.length > 15) {
                if (ipAddress.indexOf(SEPARATOR) > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","))
                }
            }
        } catch (ex: Exception) {
            ipAddress = ""
        }
        return ipAddress ?: ""
    }
}
