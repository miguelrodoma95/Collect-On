package com.miwoapp.collecton.Fragments.MainFeaturesFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.miwoapp.collecton.R
import kotlinx.android.synthetic.main.activity_main_features.*

class MyCollectionsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_my_collections, main_features_container, false)
    }


}
