package com.restarttech.task.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.restarttech.task.R
import com.restarttech.task.helper.Utils
import com.restarttech.task.model.Home
import com.restarttech.task.remote.RetrofitWebService
import com.restarttech.task.remote.response.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by µðšţãƒâ ™ on 11/4/2019.
 *  ->
 */
class HomeViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {
    val homeList: MutableLiveData<MutableList<Home>> by lazy {
        getDataRetrofit()
        MutableLiveData<MutableList<Home>>()
    }

    val success: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val progressView: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun getDataRetrofit() {
        progressView.value = true
        success.value = true
        RetrofitWebService.service.getHome().enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                val res = response.body()
                res.let {
                    val list = mutableListOf<Home>()
                    list.add(Home( mApplication.getString(R.string.hotspots), R.drawable.hotspot_icon, res!!.data.hot_spots))
                    list.add(Home( mApplication.getString(R.string.events), R.drawable.events_icon, res.data.events))
                    list.add(Home( mApplication.getString(R.string.attractions), R.drawable.attarctions_icon, res.data.attractions))
                    homeList.value = list
                    progressView.value = false
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                if (Utils.isNotConnected(t)) {
                    success.value = false
                }
                t.printStackTrace()
                progressView.value = false
            }

        })
    }
}