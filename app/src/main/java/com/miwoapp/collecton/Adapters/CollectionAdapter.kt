package com.miwoapp.collecton.Adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.miwoapp.collecton.Model.Collection
import com.miwoapp.collecton.Model.Theme
import com.miwoapp.collecton.R
import java.util.ArrayList

/**
 * Created by Miguel on 12/31/2018.
 */
class CollectionAdapter(private val collectionList: ArrayList<Collection>) : RecyclerView.Adapter<CollectionAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val view  = LayoutInflater.from(parent?.context)
                .inflate(R.layout.adapter_collection, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return collectionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        var collection: Collection = collectionList[position]

        holder?.tvCollectionName?.text = collection.collection_name
    }

    inner class MyViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val collectionView = mView
        val tvCollectionName = mView.findViewById<TextView>(R.id.tv_collection_name)
    }
}