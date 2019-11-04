package com.restarttech.task.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.restarttech.task.R
import kotlinx.android.synthetic.main.fragment_other.*

/**
 * Created by µðšţãƒâ ™ on 11/4/2019.
 *  ->
 */
class OtherFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle.text = tag
    }
}