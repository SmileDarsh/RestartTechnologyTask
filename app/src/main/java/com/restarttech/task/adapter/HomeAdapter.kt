package com.restarttech.task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.restarttech.task.R
import com.restarttech.task.model.Home
import kotlinx.android.synthetic.main.item_home.view.*

/**
 * Created by µðšţãƒâ ™ on 11/4/2019.
 *  ->
 */
class HomeAdapter(private val mHomeList: MutableList<Home>): RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false))

    override fun getItemCount(): Int = mHomeList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) = holder.bindView(mHomeList[position])
}

class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mIvIcon = itemView.ivIcon
    private val mTvName = itemView.tvName
    private val mTvCategories = itemView.rvCategories

    fun bindView(item: Home) {
        Glide.with(itemView.context)
            .load(item.icon)
            .into(mIvIcon)

        mTvName.text = item.name
        mTvCategories.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = HomeCategoryAdapter(item.categories)
        }
    }
}