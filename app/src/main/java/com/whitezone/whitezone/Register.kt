package com.whitezone.whitezone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    lateinit var  auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        sign_up_button.setOnClickListener {
            signUpUser()
        }
        toLogin(to_login_text)

    }

    fun toLogin(v: View): Unit{
        v.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
            finish()
        }
    }

    private fun signUpUser() {
        if (tv_email.text.toString().isEmpty()) {
            tv_email.error = "Please enter the email"
            tv_email.requestFocus()
            return

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(tv_email.text.toString()).matches()) {
            tv_email.error = "Please enter valid email"
            tv_email.requestFocus()
            return

        }

        if (tv_password.text.toString().isEmpty()) {
            tv_password.error = "Please enter password"
            tv_password.requestFocus()
            return

        }

        if (tv_password_validation.toString().compareTo(tv_password.text.toString()) == 0) {
            tv_password_validation.error = "Please enter the same password"
            tv_password_validation.requestFocus()
            return

        }

        auth.createUserWithEmailAndPassword(tv_email.text.toString(), tv_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        var name = user.displayName
                    }


                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, Login::class.java))
                                finish()
                            }
                        }

                    Toast.makeText(baseContext, "Sign Up success",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Sign Up failed. Try again next time.",Toast.LENGTH_LONG).show()
                }
            }



    }
}