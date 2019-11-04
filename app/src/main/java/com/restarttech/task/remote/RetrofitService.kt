package com.restarttech.task.remote

import com.restarttech.task.remote.response.DataResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by µðšţãƒâ ™ on 11/4/2019.
 *  ->
 */
interface RetrofitService {
    @GET("home")
    fun getHome(): Call<DataResponse>
}