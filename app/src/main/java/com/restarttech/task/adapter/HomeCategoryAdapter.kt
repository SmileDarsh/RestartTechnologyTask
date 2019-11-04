package com.restarttech.task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.restarttech.task.R
import com.restarttech.task.model.Data
import kotlinx.android.synthetic.main.item_home_category.view.*

/**
 * Created by µðšţãƒâ ™ on 11/4/2019.
 *  ->
 */
class HomeCategoryAdapter(private val mCategories: MutableList<Data>) :
    RecyclerView.Adapter<HomeCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryViewHolder =
        HomeCategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_home_category,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = mCategories.size

    override fun onBindViewHolder(holder: HomeCategoryViewHolder, position: Int) =
        holder.bindView(mCategories[position])

}

class HomeCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mIvPhoto = itemView.ivPhoto
    private val mTvName = itemView.tvName
    private val mTvCategory = itemView.tvCategory

    fun bindView(item: Data) {
        if (item.photos.size > 0)
            Glide.with(itemView.context)
                .load(item.photos[0])
                .into(mIvPhoto)

        mTvName.text = item.name
        mTvCategory.text = if(item.categories.size > 0) item.categories[0].name else ""
    }
}