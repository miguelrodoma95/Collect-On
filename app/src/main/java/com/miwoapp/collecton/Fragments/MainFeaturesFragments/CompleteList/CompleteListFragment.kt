package com.miwoapp.collecton.Fragments.MainFeaturesFragments.CompleteList


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.miwoapp.collecton.Model.Theme

import com.miwoapp.collecton.R
import kotlinx.android.synthetic.main.activity_main_features.*
import java.util.ArrayList
import com.google.firebase.database.DataSnapshot
import com.miwoapp.collecton.Adapters.CompleteListAdapter


class CompleteListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_complete_list, main_features_container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val ref: DatabaseReference = database.getReference("Themes")

        readFromFirebaseDatabase(ref)
    }

    private fun readFromFirebaseDatabase(ref: DatabaseReference) {
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var themeList = ArrayList<Theme>()

                for(ds in dataSnapshot.children){
                    val themeName = ds.key
                    val themeLogo = dataSnapshot.child(themeName!!).child("Logo").value

                    var theme = Theme()

                    theme.themeName = themeName
                    theme.Logo = themeLogo.toString()

                    themeList.add(theme)
                }

                displayListWithRecyclerView(themeList)
            }
        })
    }

    private fun displayListWithRecyclerView(themeList: ArrayList<Theme>) {
        val rvCompleteList = activity!!.findViewById<RecyclerView>(R.id.rv_complete_list)
        val adapter = CompleteListAdapter(themeList)

        val llManager = LinearLayoutManager(context)

        rvCompleteList.layoutManager = llManager
        rvCompleteList.adapter = adapter
    }
}
