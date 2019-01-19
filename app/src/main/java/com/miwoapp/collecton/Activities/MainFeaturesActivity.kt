package com.miwoapp.collecton.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.miwoapp.collecton.Fragments.MainFeaturesFragments.CommunityFragment
import com.miwoapp.collecton.Fragments.MainFeaturesFragments.CompleteList.CompleteListFragment
import com.miwoapp.collecton.Fragments.MainFeaturesFragments.ExchangeFragment
import com.miwoapp.collecton.Fragments.MainFeaturesFragments.ProfileFragment
import com.miwoapp.collecton.R

class MainFeaturesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_features)

        bottomNavigationView(savedInstanceState)

    }

    private fun bottomNavigationView(savedInstanceState: Bundle?) {
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        val completeListFragment = CompleteListFragment()
        val myCollectionsFragment = ProfileFragment()
        val exchangeFragment = ExchangeFragment()
        val communityFragment = CommunityFragment()

        if(savedInstanceState == null){
            val fragmenrTransaction = supportFragmentManager.beginTransaction()
            val fragment = completeListFragment
            fragmenrTransaction.replace(R.id.main_features_container, fragment).commit()
        }

        bottomNavView.setOnNavigationItemSelectedListener { item ->
            var selectedItem = item.itemId
            var fragment: Fragment? = null
            val fragmenrTransaction = supportFragmentManager.beginTransaction()

            when(selectedItem){
                R.id.complete_list -> fragment = completeListFragment
                R.id.my_collections -> fragment = myCollectionsFragment
                R.id.exchange -> fragment = exchangeFragment
                R.id.community -> fragment = communityFragment
            }
            fragmenrTransaction.replace(R.id.main_features_container, fragment).commit()
            true
        }


    }
}
