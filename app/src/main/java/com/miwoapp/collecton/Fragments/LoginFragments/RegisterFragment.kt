package com.miwoapp.collecton.Fragments.LoginFragments


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
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
import com.miwoapp.collecton.Activities.MainFeaturesActivity

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
        clearErrorMessages()
    }

    private fun clearErrorMessages() {
        val etName = activity!!.findViewById<EditText>(R.id.et_name)
        val etLastName = activity!!.findViewById<EditText>(R.id.et_lastname)
        val etEmail = activity!!.findViewById<EditText>(R.id.et_email_register)
        val etPassword = activity!!.findViewById<EditText>(R.id.et_password_register)
        val tilName = activity!!.findViewById<TextInputLayout>(R.id.til_name)
        val tilLastName= activity!!.findViewById<TextInputLayout>(R.id.til_lastname)
        val tilEmail = activity!!.findViewById<TextInputLayout>(R.id.til_email_register)
        val tilPassword = activity!!.findViewById<TextInputLayout>(R.id.til_password_register)

        etName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tilName.error = null
            }
        })

        etLastName.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tilLastName.error = null
            }
        })

        etEmail.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tilEmail.error = null
            }
        })

        etPassword.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tilPassword.error = null
            }
        })

    }

    private fun registerClickListener() {
        val etName = activity!!.findViewById<EditText>(R.id.et_name)
        val etLastName = activity!!.findViewById<EditText>(R.id.et_lastname)
        val etEmail = activity!!.findViewById<EditText>(R.id.et_email_register)
        val etPassword = activity!!.findViewById<EditText>(R.id.et_password_register)
        val btnCreateAccount = activity!!.findViewById<Button>(R.id.btn_create_account)

        btnCreateAccount.setOnClickListener {
            var name = etName.text.toString()
            var lastName = etLastName.text.toString()
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            if(fieldsAreEmpty(name, lastName, email, password)){
                displayError(name,lastName, email, password)
            } else {
                registerWithFirebase(email, password)
            }
        }
    }

    private fun displayError(name: String, lastName: String, email: String, password: String) {
        val tilName = activity!!.findViewById<TextInputLayout>(R.id.til_name)
        val tilLastName = activity!!.findViewById<TextInputLayout>(R.id.til_lastname)
        val tilEmail = activity!!.findViewById<TextInputLayout>(R.id.til_email_register)
        val tilPassword = activity!!.findViewById<TextInputLayout>(R.id.til_password_register)

        if (TextUtils.isEmpty(name)) tilName.error = resources.getString(R.string.error_required_field)
        if (TextUtils.isEmpty(lastName)) tilLastName.error = resources.getString(R.string.error_required_field)
        if (TextUtils.isEmpty(email)) tilEmail.error = resources.getString(R.string.error_required_field)
        if (TextUtils.isEmpty(password)) tilPassword.error = resources.getString(R.string.error_required_field)
    }

    private fun fieldsAreEmpty(name: String, lastName: String, email: String, password: String): Boolean {
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName)
                || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            return true
        }
        return false
    }

    private fun registerWithFirebase(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity!!, object : OnCompleteListener<AuthResult>{
                    override fun onComplete(task: Task<AuthResult>) {
                        if(task.isSuccessful()){
                            Toast.makeText(activity!!, "Register succesful", Toast.LENGTH_SHORT).show()
                            val user: FirebaseUser = firebaseAuth.currentUser!!

                            goToMainActivity()
                        } else {
                            Toast.makeText(activity!!, "Register failed", Toast.LENGTH_SHORT).show()

                        }
                    }

                })
    }

    private fun goToMainActivity() {
        val mainActivityIntent = Intent(activity!!, MainFeaturesActivity::class.java)
        startActivity(mainActivityIntent)
        activity!!.finish()
    }

    private fun toolbarStatus() {
        val tvToolbarTitle = activity!!.findViewById<TextView>(R.id.tv_toolbar_title_login)
        tvToolbarTitle.text = resources.getString(R.string.register_toolbar)
    }
}