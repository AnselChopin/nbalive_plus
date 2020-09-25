package com.qyb.nbalive.util


/**
 * author: Qing
 * time: 2020/9/17 10:49
 * desc:
 *      简单加密解密
 */

object MyHrefEncodeUtil {

    //加密
    fun encode(href: String): String{
        return if(href.isNotEmpty()){
            var result = if(href.startsWith("https://")){
                href.replace("https://","qybs")
            }else{
                href.replace("http://","qyb")
            }
            result = result.replace("www.","ansel")
            result = result.replace(".com/","chopin")
            result
        }else{
            href
        }
    }

    //解密
    fun decode(decodeHref: String): String{
        return if(decodeHref.isNotEmpty()){
            var result = if(decodeHref.startsWith("qybs")){
                decodeHref.replace("qybs","https://")
            }else{
                decodeHref.replace("qyb","http://")
            }
            result = result.replace("ansel","www.")
            result = result.replace("chopin",".com/")
            result
        }else{
            decodeHref
        }
    }
}