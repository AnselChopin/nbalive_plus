var device_model;
var device_os;
var user_agent;
var version_webkit;
var version_build;


$(document).ready(function () {
    getDeviceInfo();
    uploadLog()
});

function getDeviceInfo() {
    var device_type = navigator.userAgent;//获取userAgent信息
    var md = new MobileDetect(device_type);//初始化mobile-detect
    var os = md.os();//获取系统
    // model:手机型号  ver:系统版本
    var model = "", ver = '';
    if (os == "iOS") {//ios系统的处理
        model = md.mobile();
        if (model == "iPhone") {
            ver = device_type.toLocaleLowerCase().match(/cpu iphone os (.*?) like mac os/);
            ver = ver[1].replace(/_/g, ".");
        }
    } else if (os == "AndroidOS") {//Android系统的处理
        var j, sss = device_type.split(";");
        for (var i = 0; i < sss.length; i++) {
            if (sss[i].indexOf("Build/") > 0) {
                j = i;
                break;
            }
        }
        if (j > -1) {
            model = sss[j].substring(0, sss[j].indexOf("Build/"));
        }
    }

    device_model = model;
    device_os = os;
    user_agent = md.userAgent();
    version_webkit = md.version('Webkit');
    version_build = md.versionStr('Build');

}


function uploadLog() {
    console.log("666");
    doPost(API_LIVE_LOG,
        {
            "device_model": device_model,
            "device_os": device_os,
            "user_agent": user_agent,
            "version_webkit": version_webkit,
            "version_build": version_build
        }, function (result) {
            console.log("log done")
        });
}