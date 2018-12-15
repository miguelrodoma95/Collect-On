package com.miwoapp.collecton.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.miwoapp.collecton.Fragments.LoginFragments.LoginFragment
import com.miwoapp.collecton.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inflateLoginFragment()
    }

    private fun inflateLoginFragment() {
        val loginFragment = LoginFragment()
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction
                .replace(R.id.container_login, loginFragment)
                .commit()
    }
}
