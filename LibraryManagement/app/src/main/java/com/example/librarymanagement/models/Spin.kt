package com.example.librarymanagement.models

import java.io.Serializable

class Spin (
    var id: String ?= null,
    var value: String?= null,
    var isSignal: Long = 0,
    var code: String?=null,
    var number:Int = 0,
    var taskId: Long? = 0L,
    var checkSelect: Long ?= 0,
    val checkCatWorkTypeId: Long = 0,
    var listRole: String?=null,
): Serializable {
    override fun toString(): String {
        return "$value"
    }
}