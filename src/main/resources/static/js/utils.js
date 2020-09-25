// 工具类

/**
 *  GET
 * @param apiUrl    请求地址
 * @param callBack  回调方法
 */
function doGet(apiUrl,callBack) {
    $.ajax({
        type: "GET",
        url: apiUrl,
        success: function (result) {
            callBack(result)
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            // alert(e);
        }
    });
}

/**
 *  POST
 * @param argsObject json格式参数
 * @param apiUrl    请求地址
 * @param callBack  回调方法
 */
function doPost(apiUrl,argsObject,callBack) {
    $.ajax({
        //请求方式
        type: "POST",
        contentType: "application/json;charset=UTF-8",
        dataType:"json",
        data:JSON.stringify(argsObject),
        url: apiUrl,
        //请求成功
        success: function (result) {
            callBack(result);
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            // alert(e);
        }
    });
}

/**
 *  跳转页面
 * @param url   要跳转的页面地址
 */
function loadUrl(url) {
    $(window).attr("location",url);
}