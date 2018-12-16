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

class LoginFragment : Fragment() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_login, container_login, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        clickListeners()
        toolbarStatus()
        clearErrorMessages()
    }

    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            goToMainActivity()
        }
    }

    private fun clearErrorMessages() {
        val tilEmail = activity!!.findViewById<TextInputLayout>(R.id.til_email)
        val tilPassword = activity!!.findViewById<TextInputLayout>(R.id.til_password)
        val etEmail = activity!!.findViewById<EditText>(R.id.et_email)
        val etPassword = activity!!.findViewById<EditText>(R.id.et_password)

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tilEmail.error = null
            }
        })

        etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tilPassword.error = null
            }
        })
    }

    private fun toolbarStatus() {
        var tvToolbarTitle = activity!!.findViewById<TextView>(R.id.tv_toolbar_title_login)
        tvToolbarTitle.text = resources.getString(R.string.app_name_toolbar)
    }

    private fun clickListeners() {
        val btnLogin = activity!!.findViewById<Button>(R.id.btn_login)
        val btnRegister = activity!!.findViewById<Button>(R.id.btn_register)
        val etEmail = activity!!.findViewById<EditText>(R.id.et_email)
        val etPassword = activity!!.findViewById<EditText>(R.id.et_password)

        btnLogin.setOnClickListener{
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()

            if(fieldsAreEmpty(email, password)){
                displayError(email, password)
            } else {
                loginWithFirebase(email, password)
            }
        }

        btnRegister.setOnClickListener {
            val registerFragment = RegisterFragment()

            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.
                    replace(R.id.container_login, registerFragment)
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun displayError(email: String, password: String) {
        if (TextUtils.isEmpty(email)) {
            val tilEmail = activity!!.findViewById<TextInputLayout>(R.id.til_email)
            tilEmail.error = resources.getString(R.string.error_required_field)
        }
        if (TextUtils.isEmpty(password)) {
            val tilPassword = activity!!.findViewById<TextInputLayout>(R.id.til_password)
            tilPassword.error = resources.getString(R.string.error_required_field)
        }
    }

    private fun fieldsAreEmpty(email: String, password: String): Boolean {
        return TextUtils.isEmpty(email) || TextUtils.isEmpty(password)
    }

    private fun loginWithFirebase(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity!!, object : OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {
                        if(task.isSuccessful()) {
                            Toast.makeText(activity!!, "Login succesful", Toast.LENGTH_SHORT).show()
                            var user: FirebaseUser = firebaseAuth.currentUser!!
                            goToMainActivity()
                        } else {
                            Toast.makeText(activity!!, "Login failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
    }

    private fun goToMainActivity() {
        val mainActivityIntent = Intent(activity!!, MainFeaturesActivity::class.java)
        startActivity(mainActivityIntent)
        activity!!.finish()
    }
}
