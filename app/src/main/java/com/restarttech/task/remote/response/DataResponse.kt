package com.restarttech.task.remote.response

import com.restarttech.task.model.Data

/**
 * Created by µðšţãƒâ ™ on 11/4/2019.
 *  ->
 */
data class DataResponse(val status_code: Int, val message: String, val data: DataList)

class DataList {
    var attractions: MutableList<Data> = mutableListOf()
    var events: MutableList<Data> = mutableListOf()
    var hot_spots: MutableList<Data> = mutableListOf()
}