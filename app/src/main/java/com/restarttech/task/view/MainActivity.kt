package com.restarttech.task.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.restarttech.task.R
import com.restarttech.task.fragment.*
import com.restarttech.task.helper.BottomNavigation
import com.restarttech.task.helper.OnBottomNavigationItemClicked
import kotlinx.android.synthetic.main.bottom_navigation.*
import kotlinx.android.synthetic.main.item_sub_attractions.*
import kotlinx.android.synthetic.main.item_sub_events.*
import kotlinx.android.synthetic.main.item_sub_hotspot.*
import kotlinx.android.synthetic.main.item_sub_map.*

class MainActivity : AppCompatActivity(), OnBottomNavigationItemClicked{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment(HomeFragment(), getString(R.string.home_fragment))

        BottomNavigation(this, this)
    }

    private fun openFragment(fragment: Fragment, fragmentTag: String = "") {
        supportFragmentManager.let {
            val transaction = it.beginTransaction()
            transaction.replace(R.id.frameLayout, fragment, fragmentTag)

            transaction.commit()
        }
    }

    override fun onItemClicked(v: View) {
        when(v.id){
            ibnHome.id -> openFragment(HomeFragment(), getString(R.string.home_fragment))
            ibnSearch.id -> openFragment(OtherFragment(), getString(R.string.search_fragment))
            ibnNotification.id -> openFragment(OtherFragment(), getString(R.string.notification_fragment))
            ibnProfile.id -> openFragment(OtherFragment(), getString(R.string.profile_fragment))
            llHotspots.id -> Toast.makeText(this@MainActivity, R.string.hotspots, Toast.LENGTH_SHORT).show()
            llEvents.id -> Toast.makeText(this@MainActivity, R.string.events, Toast.LENGTH_SHORT).show()
            llAttarctions.id -> Toast.makeText(this@MainActivity, R.string.attractions, Toast.LENGTH_SHORT).show()
            llMap.id -> Toast.makeText(this@MainActivity, R.string.map, Toast.LENGTH_SHORT).show()
        }
    }
}
