package com.miwoapp.collecton.Fragments.MainFeaturesFragments.CompleteList


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.miwoapp.collecton.Model.Collectible
import com.miwoapp.collecton.Model.Collection

import com.miwoapp.collecton.R
import kotlinx.android.synthetic.main.activity_main_features.*

class CollectionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_collection, main_features_container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val collectibleViewModel = activity.run {
            ViewModelProviders.of(this!!).get(Collectible::class.java)
        }

        val themeName = arguments.getString("THEME_NAME")
        //collectibleViewModel.theme = themeName

        //readFromFirebaseDatabase(ref, themeName)

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("Themes")
                .child(themeName)
                .child("Categories")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var collectionList = ArrayList<Collection>()

                for (ds in dataSnapshot.children){
                    var collectionName = ds.key
                }
            }
        })
    }
}
