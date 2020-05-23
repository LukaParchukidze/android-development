package com.example.midtermn1.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import com.example.midtermn1.ChooseActivity
import com.example.midtermn1.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        logInButton.setOnClickListener {
            val email = emailLoginEditText.text.toString()
            val password = passwordLoginEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInAccount(email, password)
            } else {
                toastText("Fill all fields")
            }
        }

        openRegisterActivityButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun signInAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    toastText(task.exception?.message.toString())
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            toastText("Signed in as ${user.email}")
            openChooseActivity()
        }
    }

    private fun openChooseActivity(){
        val intent = Intent(this, ChooseActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toastText(text: String) {
        return Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

}
