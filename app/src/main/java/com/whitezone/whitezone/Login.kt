package com.whitezone.whitezone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class Login : AppCompatActivity() {
    lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        sign_in_button.setOnClickListener {
            signInUser()
        }
        onClick(sign_up_text)
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?){
        if(currentUser != null){
                startActivity(Intent(this,MainActivity::class.java))
                finish()
        }



    }

    fun onClick(v: View){
        v.setOnClickListener {
            startActivity(Intent(this,Register::class.java))
            finish()
        }
    }

    private fun signInUser() {
        if (email.text.toString().isEmpty()) {
            email.error = "Please enter the email"
            email.requestFocus()
            return

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
            email.error = "Please enter valid email"
            email.requestFocus()
            return

        }

        if (password.text.toString().isEmpty()) {
            password.error = "Please enter password"
            password.requestFocus()
            return

        }

        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        if(user.isEmailVerified)
                            updateUI(user)
                        if(!user.isEmailVerified) {
                            Toast.makeText(
                                baseContext,
                                "Please verify your email",
                                Toast.LENGTH_LONG
                            ).show()
                            updateUI(null)
                        }
                    }
                } else {
                    Toast.makeText(baseContext, "Login failed. Please try again later.", Toast.LENGTH_LONG).show()
                    updateUI(null)
                }
            }


    }


}