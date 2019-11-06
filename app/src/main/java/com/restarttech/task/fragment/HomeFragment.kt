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
import com.restarttech.task.model.Home
import com.restarttech.task.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

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
        tvCity.text = getString(R.string.city)

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

    private fun hideView() {
        tvEmptyList.visibility = View.GONE
        btnRetry.visibility = View.GONE
    }

    private fun showView() {
        tvEmptyList.visibility = View.VISIBLE
        btnRetry.visibility = View.VISIBLE
    }
}