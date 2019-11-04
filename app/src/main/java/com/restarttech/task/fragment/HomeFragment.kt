package com.restarttech.task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.restarttech.task.R
import com.restarttech.task.adapter.HomeAdapter
import com.restarttech.task.helper.Utils.isNotConnected
import com.restarttech.task.model.Home
import com.restarttech.task.remote.RetrofitWebService
import com.restarttech.task.remote.response.DataResponse
import com.restarttech.task.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by µðšţãƒâ ™ on 11/4/2019.
 *  ->
 */
class HomeFragment : Fragment() {

    private lateinit var model: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = ViewModelProvider(this).get(HomeViewModel::class.java)

        val nameObserver = Observer<MutableList<Home>> { list ->
            initRecyclerView(list)
        }

        val successObserver = Observer<Boolean> { success ->
            if (success)
                hideView()
            else
                showView()
        }

        val progressObserver = Observer<Boolean> { progress ->
            if (progress)
                pbProgress.visibility = View.VISIBLE
            else
                pbProgress.visibility = View.GONE
        }

        model.homeList.observe(this, nameObserver)
        model.success.observe(this, successObserver)
        model.progressView.observe(this, progressObserver)

        btnRetry.setOnClickListener {
            model.getDataRetrofit()
        }
    }

    private fun initRecyclerView(list: MutableList<Home>) {
        rvHome.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = HomeAdapter(list)
        }
    }

    private fun getDataRetrofit() {
        hideView()
        RetrofitWebService.service.getHome().enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                val res = response.body()

                res.let {
                    model.homeList.value!!.add(
                        Home(
                            "Hotspots",
                            R.drawable.hotspot_icon,
                            res!!.data.hot_spots
                        )
                    )
                    model.homeList.value!!.add(
                        Home(
                            "Events",
                            R.drawable.events_icon,
                            res.data.events
                        )
                    )
                    model.homeList.value!!.add(
                        Home(
                            "Attractions",
                            R.drawable.attarctions_icon,
                            res.data.attractions
                        )
                    )
//                    mList.add(Home("Hotspots",R.drawable.hotspot_icon, res!!.data.hot_spots))
//                    mList.add(Home("Events",R.drawable.events_icon, res.data.events))
//                    mList.add(Home("Attractions",R.drawable.attarctions_icon, res.data.attractions))

//                    mAdapter.notifyDataSetChanged()
                    pbProgress.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                if (isNotConnected(t)) {
                    showView()
                }
                t.printStackTrace()
                pbProgress.visibility = View.GONE
            }

        })
    }

    private fun hideView() {
        tvEmptyList.visibility = View.GONE
        btnRetry.visibility = View.GONE
    }

    private fun showView() {
        tvEmptyList.visibility = View.VISIBLE
        btnRetry.visibility = View.VISIBLE
    }
}