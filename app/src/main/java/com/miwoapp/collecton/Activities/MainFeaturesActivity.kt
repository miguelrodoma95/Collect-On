package com.miwoapp.collecton.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.miwoapp.collecton.Fragments.MainFeaturesFragments.CommunityFragment
import com.miwoapp.collecton.Fragments.MainFeaturesFragments.CompleteList.CompleteListFragment
import com.miwoapp.collecton.Fragments.MainFeaturesFragments.ExchangeFragment
import com.miwoapp.collecton.Fragments.MainFeaturesFragments.MyCollectionsFragment
import com.miwoapp.collecton.R

class MainFeaturesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_features)

        navigatIonDrawer()
        bottomNavigationView(savedInstanceState)

    }

    private fun bottomNavigationView(savedInstanceState: Bundle?) {
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        val completeListFragment = CompleteListFragment()
        val myCollectionsFragment = MyCollectionsFragment()
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

    private fun navigatIonDrawer() {
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navDrawer = findViewById<NavigationView>(R.id.nav_drawer)
        val firebaseAuth = FirebaseAuth.getInstance()

        navDrawer.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var id = item.itemId
                var fragment: Fragment? = null
                when(id){
                    R.id.profile_drawer -> {
                        //fragment = profileFragment
                    }
                    R.id.about_drawer -> {
                        //fragment = aboutFragment
                    }
                    R.id.fb_drawer -> {
                        //fragment = fbFragment
                    }
                    R.id.twitter_drawer -> {
                        //fragment = twiiterFragment
                    }
                    R.id.logout_drawer -> {
                        firebaseAuth.signOut()

                        val loginIntent = Intent(this@MainFeaturesActivity,
                                LoginActivity::class.java)

                        startActivity(loginIntent)

                        finish()
                    }

                }
                return true
            }

        })
    }
}
