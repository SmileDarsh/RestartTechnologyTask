package com.restarttech.task.remote

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by µðšţãƒâ ™ on 11/4/2019.
 *  ->
 */

class RetrofitWebService private constructor() {

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .writeTimeout(10, TimeUnit.MINUTES)
            .readTimeout(10, TimeUnit.MINUTES)
            .addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mServices[url] = retrofit.create(RetrofitService::class.java)
    }

    companion object {
        private val TAG = RetrofitWebService::class.java.simpleName
        private val mServices = HashMap<String, RetrofitService>()
        private const val url = "http://tourista.167.99.5.134.xip.io/api/"

        val service: RetrofitService
            get() {
                if (null == mServices[url]) {
                    RetrofitWebService()
                }
                return mServices[url] as RetrofitService
            }

        fun log(t: Throwable) {
            Log.e(TAG, TAG + if (null != t.message) t.message else t.toString())
        }

        fun <T> callback(fn: (Throwable?, Response<T>?) -> Unit): Callback<T> {
            return object : Callback<T> {
                override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) =
                    fn(null, response)

                override fun onFailure(call: Call<T>, t: Throwable) = fn(t, null)
            }
        }
    }
}
