package com.restarttech.task.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.restarttech.task.R
import com.restarttech.task.fragment.*
import com.restarttech.task.helper.BottomNavigation
import com.restarttech.task.helper.OnBottomNavigationItemClicked
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_navigation.*

class MainActivity : AppCompatActivity(), OnBottomNavigationItemClicked{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCity.text = getString(R.string.city)
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
        }
    }
}
