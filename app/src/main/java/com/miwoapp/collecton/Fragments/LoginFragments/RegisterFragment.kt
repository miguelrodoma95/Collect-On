package com.miwoapp.collecton.Fragments.LoginFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import com.miwoapp.collecton.R
import kotlinx.android.synthetic.main.activity_login.*

class RegisterFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_register, container_login, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        toolbarStatus()
        registerClickListener()
    }

    private fun registerClickListener() {
        val etName = activity!!.findViewById<EditText>(R.id.et_name)
        val etLastName = activity!!.findViewById<EditText>(R.id.et_lastname)
        val etEmail = activity!!.findViewById<EditText>(R.id.et_user_email)
        val etPassword = activity!!.findViewById<EditText>(R.id.et_user_password)
        val btnCreateAccount = activity!!.findViewById<Button>(R.id.btn_create_account)

        btnCreateAccount.setOnClickListener {
            var name = etName.text.toString()
            var lastName = etLastName.text.toString()
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity!!, object : OnCompleteListener<AuthResult>{
                        override fun onComplete(task: Task<AuthResult>) {
                            if(task.isSuccessful()){
                                Toast.makeText(activity!!, "Register succesful", Toast.LENGTH_SHORT).show()
                                val user: FirebaseUser = firebaseAuth.currentUser!!
                                //Todo: go to home screen with user info
                            } else {
                                Toast.makeText(activity!!, "Register failed", Toast.LENGTH_SHORT).show()

                            }
                        }

                    })
        }

    }

    private fun toolbarStatus() {
        val tvToolbarTitle = activity!!.findViewById<TextView>(R.id.tv_toolbar_title_login)
        tvToolbarTitle.text = resources.getString(R.string.register_toolbar)
    }
}