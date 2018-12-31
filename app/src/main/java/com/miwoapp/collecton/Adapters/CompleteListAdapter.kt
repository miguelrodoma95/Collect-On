package com.miwoapp.collecton.Adapters

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.miwoapp.collecton.Fragments.MainFeaturesFragments.CompleteList.CollectionFragment
import com.miwoapp.collecton.Model.Theme
import com.miwoapp.collecton.R
import com.squareup.picasso.Picasso
import java.util.ArrayList

/**
 * Created by Miguel on 12/20/2018.
 */
class CompleteListAdapter(private val themeList: ArrayList<Theme>) : RecyclerView.Adapter<CompleteListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view  = LayoutInflater.from(parent?.context)
                .inflate(R.layout.adapter_complete_list, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return themeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        var theme = themeList[position]
        holder?.tvThemeName?.text = theme.themeName
        Picasso.get()
                .load(theme.Logo)
                .noFade()
                .into(holder?.ivThemeLogo)

        holder?.themeView?.setOnClickListener {view ->
            val collectionFragment = CollectionFragment()

            val b = Bundle()

            b.putString("THEME_NAME", theme.themeName)

            collectionFragment.arguments = b

            val activity = view.context as AppCompatActivity
            activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.main_features_container, collectionFragment)
                    .addToBackStack(null)
                    .commit()
        }
    }

    inner class MyViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val themeView = mView
        val tvThemeName = mView.findViewById<TextView>(R.id.tv_theme_name)
        val ivThemeLogo = mView.findViewById<ImageView>(R.id.iv_theme_logo_complete_list)
    }
}