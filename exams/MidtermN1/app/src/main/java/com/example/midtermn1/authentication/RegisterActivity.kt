package com.example.midtermn1.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import com.example.midtermn1.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        registerButton.setOnClickListener {
            val email = emailRegisterEditText.text.toString()
            val password = passwordRegisterEditText.text.toString()
            val rePassword = rePasswordRegisterEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && rePassword.isNotEmpty()) {
                if (password == rePassword) {
                    createAccount(email, password)
                } else {
                    toastText("Passwords didn't match")
                }
            } else {
                toastText("Fill all fields")
            }
        }

        openLoginActivityButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun createAccount(email: String, password: String) {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    d("TAG", "createUserWithEmail:success")
                    toastText("User successfully registered")
                } else {
                    toastText(task.exception?.message.toString())
                }
            }
    }

    private fun toastText(text: String) {
        return Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
