/** layui-v2.5.6 MIT License By https://www.layui.com */
;layui.define("jquery", function (e) {
    "use strict";
    var t = layui.$, i = {
        fixbar: function (e) {
            var i, n, a = "layui-fixbar", o = "layui-fixbar-top", r = t(document), l = t("body");
            e = t.extend({showHeight: 200}, e), e.bar1 = e.bar1 === !0 ? "&#xe606;" : e.bar1, e.bar2 = e.bar2 === !0 ? "&#xe607;" : e.bar2, e.bgcolor = e.bgcolor ? "background-color:" + e.bgcolor : "";
            var c = [e.bar1, e.bar2, "&#xe604;"], u = t(['<ul class="' + a + '">', e.bar1 ? '<li class="layui-icon" lay-type="bar1" style="' + e.bgcolor + '">' + c[0] + "</li>" : "", e.bar2 ? '<li class="layui-icon" lay-type="bar2" style="' + e.bgcolor + '">' + c[1] + "</li>" : "", '<li class="layui-icon ' + o + '" lay-type="top" style="' + e.bgcolor + '">' + c[2] + "</li>", "</ul>"].join("")), g = u.find("." + o), s = function () {
                var t = r.scrollTop();
                t >= e.showHeight ? i || (g.show(), i = 1) : i && (g.hide(), i = 0)
            };
            t("." + a)[0] || ("object" == typeof e.css && u.css(e.css), l.append(u), s(), u.find("li").on("click", function () {
                var i = t(this), n = i.attr("lay-type");
                "top" === n && t("html,body").animate({scrollTop: 0}, 200), e.click && e.click.call(this, n)
            }), r.on("scroll", function () {
                clearTimeout(n), n = setTimeout(function () {
                    s()
                }, 100)
            }))
        }, countdown: function (e, t, i) {
            var n = this, a = "function" == typeof t, o = new Date(e).getTime(), r = new Date(!t || a ? (new Date).getTime() : t).getTime(), l = o - r, c = [Math.floor(l / 864e5), Math.floor(l / 36e5) % 24, Math.floor(l / 6e4) % 60, Math.floor(l / 1e3) % 60];
            a && (i = t);
            var u = setTimeout(function () {
                n.countdown(e, r + 1e3, i)
            }, 1e3);
            return i && i(l > 0 ? c : [0, 0, 0, 0], t, u), l <= 0 && clearTimeout(u), u
        }, timeAgo: function (e, t) {
            var i = this, n = [[], []], a = (new Date).getTime() - new Date(e).getTime();
            return a > 26784e5 ? (a = new Date(e), n[0][0] = i.digit(a.getFullYear(), 4), n[0][1] = i.digit(a.getMonth() + 1), n[0][2] = i.digit(a.getDate()), t || (n[1][0] = i.digit(a.getHours()), n[1][1] = i.digit(a.getMinutes()), n[1][2] = i.digit(a.getSeconds())), n[0].join("-") + " " + n[1].join(":")) : a >= 864e5 ? (a / 1e3 / 60 / 60 / 24 | 0) + "天前" : a >= 36e5 ? (a / 1e3 / 60 / 60 | 0) + "小时前" : a >= 18e4 ? (a / 1e3 / 60 | 0) + "分钟前" : a < 0 ? "未来" : "刚刚"
        }, digit: function (e, t) {
            var i = "";
            e = String(e), t = t || 2;
            for (var n = e.length; n < t; n++)i += "0";
            return e < Math.pow(10, t) ? i + (0 | e) : e
        }, toDateString: function (e, t) {
            var i = this, n = new Date(e || new Date), a = [i.digit(n.getFullYear(), 4), i.digit(n.getMonth() + 1), i.digit(n.getDate())], o = [i.digit(n.getHours()), i.digit(n.getMinutes()), i.digit(n.getSeconds())];
            return t = t || "yyyy-MM-dd HH:mm:ss", t.replace(/yyyy/g, a[0]).replace(/MM/g, a[1]).replace(/dd/g, a[2]).replace(/HH/g, o[0]).replace(/mm/g, o[1]).replace(/ss/g, o[2])
        }, escape: function (e) {
            return String(e || "").replace(/&(?!#?[a-zA-Z0-9]+;)/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/'/g, "&#39;").replace(/"/g, "&quot;")
        }, event: function (e, n, a) {
            var o = t("body");
            return a = a || "click", n = i.event[e] = t.extend(!0, i.event[e], n) || {}, i.event.UTIL_EVENT_CALLBACK = i.event.UTIL_EVENT_CALLBACK || {}, o.off(a, "*[" + e + "]", i.event.UTIL_EVENT_CALLBACK[e]), i.event.UTIL_EVENT_CALLBACK[e] = function () {
                var i = t(this), a = i.attr(e);
                "function" == typeof n[a] && n[a].call(this, i)
            }, o.on(a, "*[" + e + "]", i.event.UTIL_EVENT_CALLBACK[e]), n
        }
    };
    !function (e, t, i) {
        "$:nomunge";
        function n() {
            a = t[l](function () {
                o.each(function () {
                    var t = e(this), i = t.width(), n = t.height(), a = e.data(this, u);
                    (i !== a.w || n !== a.h) && t.trigger(c, [a.w = i, a.h = n])
                }), n()
            }, r[g])
        }

        var a, o = e([]), r = e.resize = e.extend(e.resize, {}), l = "setTimeout", c = "resize", u = c + "-special-event", g = "delay", s = "throttleWindow";
        r[g] = 250, r[s] = !0, e.event.special[c] = {
            setup: function () {
                if (!r[s] && this[l])return !1;
                var t = e(this);
                o = o.add(t), e.data(this, u, {w: t.width(), h: t.height()}), 1 === o.length && n()
            }, teardown: function () {
                if (!r[s] && this[l])return !1;
                var t = e(this);
                o = o.not(t), t.removeData(u), o.length || clearTimeout(a)
            }, add: function (t) {
                function n(t, n, o) {
                    var r = e(this), l = e.data(this, u) || {};
                    l.w = n !== i ? n : r.width(), l.h = o !== i ? o : r.height(), a.apply(this, arguments)
                }

                if (!r[s] && this[l])return !1;
                var a;
                return e.isFunction(t) ? (a = t, n) : (a = t.handler, void(t.handler = n))
            }
        }
    }(t, window), e("util", i)
});