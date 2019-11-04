package com.restarttech.task.helper

/**
 * Created by µðšţãƒâ ™ on 11/4/2019.
 *  ->
 */
object Utils {
    fun isNotConnected(t: Throwable): Boolean {
        return (t.message != null && (t.message!!.contains("No address associated with hostname")
                || t.message!!.contains("connect timed out")
                || t.message!!.contains("timeout")))
    }
}