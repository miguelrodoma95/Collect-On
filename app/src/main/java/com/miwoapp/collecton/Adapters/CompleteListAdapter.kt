package com.miwoapp.collecton.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.miwoapp.collecton.R

/**
 * Created by axented on 12/20/2018.
 */
class CompleteListAdapter(val themeList: ArrayList<Themes>) : RecyclerView.Adapter<CompleteListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view  = LayoutInflater.from(parent?.context)
                .inflate(R.layout.adapter_complete_list, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return themeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class MyViewHolder(var mView: View) : RecyclerView.ViewHolder(mView) {
        val tvThemeName = mView.findViewById<TextView>(R.id.tv_theme_name)
        val ivThemeLogo = mView.findViewById<ImageView>(R.id.iv_theme_logo_complete_list)
    }
}