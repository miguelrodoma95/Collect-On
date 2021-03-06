package com.miwoapp.collecton.LoginFragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.miwoapp.collecton.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_login, container_login, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        loginUser()
    }

    private fun loginUser() {
        val btnLogin = activity!!.findViewById<Button>(R.id.btn_login)
        val etEmail = activity!!.findViewById<EditText>(R.id.et_email)
        val etPassword = activity!!.findViewById<EditText>(R.id.et_password)

        btnLogin.setOnClickListener{
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            //Todo: Check that neither of the fields is empty

            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(activity!!, object : OnCompleteListener<AuthResult> {
                        override fun onComplete(task: Task<AuthResult>) {
                            if(task.isSuccessful()) {
                                var user: FirebaseUser = firebaseAuth.currentUser!!
                                //Todo: go to home screen
                            } else {
                                Toast.makeText(activity!!, "Login failed", Toast.LENGTH_SHORT).show()
                            }
                        }

                    })
        }
    }


    override fun onStart() {
        super.onStart()

        var currentUser: FirebaseUser = firebaseAuth.currentUser!!
        //Todo send to home screen//
    }

}
