package com.restarttech.task.model

/**
 * Created by µðšţãƒâ ™ on 11/4/2019.
 * we don't need all data from webservice for this task
 */
open class Data {
    var id: Int = 0
    var name: String = ""
    var photos: MutableList<String> = mutableListOf()
    var categories: MutableList<Categories> = mutableListOf()
}