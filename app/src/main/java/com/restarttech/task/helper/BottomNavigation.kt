package com.restarttech.task.helper

import android.app.Activity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.setMargins
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu
import com.restarttech.task.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_navigation.*

/**
 * Created by µðšţãƒâ ™ on 11/4/2019.
 * Here i am Create Custom BottomNavigation for this Task
 * because it's no't BottomNavigationView or TabLayout
 */
class BottomNavigation(
    private val mActivity: Activity,
    private val mOnItemClicked: OnBottomNavigationItemClicked
) {

    private val mNavigationButtons = mutableListOf<ImageButton>()

    init {
        addButtons()
        initFloatingActionButton()
    }

    /**
    * here first you want add Bottom for BottomNavigation from MainActivity Class
    * this button it's static if you want add or update any button you will do this manual
    * */
    private fun addButtons() {
        mNavigationButtons.add(mActivity.ibnHome)
        mNavigationButtons.add(mActivity.ibnSearch)
        mNavigationButtons.add(mActivity.ibnNotification)
        mNavigationButtons.add(mActivity.ibnProfile)

        mActivity.ibnHome.setOnClickListener { onClicked(it) }
        mActivity.ibnSearch.setOnClickListener { onClicked(it) }
        mActivity.ibnNotification.setOnClickListener { onClicked(it) }
        mActivity.ibnProfile.setOnClickListener { onClicked(it) }

        mActivity.ibnHome.isSelected = true
    }

    /**
     * here my FloatingActionButton for bellman bottom and add subAction for this button
     * */
    private fun initFloatingActionButton() {
        val icon = ImageView(mActivity)
        icon.setImageDrawable(mActivity.getDrawable(R.drawable.bellman_bottom_icon))

        val actionButton = FloatingActionButton.Builder(mActivity)
            .setContentView(icon)
            .setBackgroundDrawable(R.drawable.purple_background)
            .build()

        val layoutParams = FrameLayout.LayoutParams(200, 200)
        layoutParams.setMargins(64)
        actionButton.setPosition(FloatingActionButton.POSITION_BOTTOM_CENTER, layoutParams)

        val actionMenu = FloatingActionMenu.Builder(mActivity)
            .addSubActionView(R.layout.item_sub_map, mActivity)
            .addSubActionView(R.layout.item_sub_attractions, mActivity)
            .addSubActionView(R.layout.item_sub_events, mActivity)
            .addSubActionView(R.layout.item_sub_hotspot, mActivity)
            .setStartAngle(360)
            .setEndAngle(180)
            .attachTo(actionButton)
            .build()

        actionMenu.setStateChangeListener(object : FloatingActionMenu.MenuStateChangeListener {
            override fun onMenuOpened(p0: FloatingActionMenu?) {
                mActivity.ivAction.visibility = View.VISIBLE
            }

            override fun onMenuClosed(p0: FloatingActionMenu?) {
                mActivity.ivAction.visibility = View.GONE
            }

        })
    }

    /**
     * Click listener for BottomNavigation and return value in MainActivity
     * */
    private fun onClicked(v: View) {
        var imageButtonSelected: ImageButton? = null
        val imageButtonNotSelected = mutableListOf<ImageButton>()
        mNavigationButtons.forEach {
            if (it.id == v.id) {
                imageButtonSelected = it
                mOnItemClicked.onItemClicked(v)
            }else{
                imageButtonNotSelected.add(it)
            }
        }
        imageButtonSelected!!.isSelected = true
        imageButtonNotSelected[0].isSelected = false
        imageButtonNotSelected[1].isSelected = false
        imageButtonNotSelected[2].isSelected = false
        imageButtonNotSelected.clear()
    }
}