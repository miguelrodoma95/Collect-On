package com.miwoapp.collecton.Fragments.MainFeaturesFragments


import android.graphics.Color
import android.os.Bundle
import kotlin.String
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

import com.miwoapp.collecton.R
import kotlinx.android.synthetic.main.activity_main_features.*
import android.widget.TextView



class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_my_collections, main_features_container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lvOptions = activity!!.findViewById<ListView>(R.id.lv_options_profile)

        val optionsList = ArrayList<String>()
        optionsList.add("Accont Information")
        optionsList.add("Following Collections")
        optionsList.add("My Collections")

        val adapter = object : ArrayAdapter<String>(activity,
                android.R.layout.simple_list_item_1,
                optionsList) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val view = super.getView(position, convertView, parent)
                val text = view.findViewById<View>(android.R.id.text1) as TextView

                text.setTextColor(Color.WHITE)
                text.textSize = 16.0F

                return view
            }
        }

        lvOptions.adapter = adapter
    }



}
