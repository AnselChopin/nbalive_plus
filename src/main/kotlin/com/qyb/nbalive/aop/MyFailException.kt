package com.qyb.nbalive.aop

class MyFailException : RuntimeException {

    private var code: Int? = null
    private var msg: String? = null

    constructor(code: Int, msg: String) : super(msg) {
        this.code = code
        this.msg = msg
    }

    constructor() : super()

    companion object {
        private const val serialVersionUID = -5519743897907627214L
    }
}
