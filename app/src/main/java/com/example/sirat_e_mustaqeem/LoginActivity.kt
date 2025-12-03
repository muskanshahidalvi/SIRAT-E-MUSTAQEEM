package com.example.sirat_e_mustaqeem

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent


import com.example.sirat_e_mustaqeem.R

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignUp: TextView
    private lateinit var tvContinueAdmin: TextView
    private lateinit var tvContinueGuest: TextView
    private lateinit var tvForgot: TextView
    private lateinit var pbLogin: ProgressBar

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // init views
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignUp = findViewById(R.id.tvSignUp)
        tvContinueAdmin = findViewById(R.id.tvContinueAdmin)
        tvContinueGuest = findViewById(R.id.tvContinueGuest)
        tvForgot = findViewById(R.id.tvForgot)
        pbLogin = findViewById(R.id.pbLogin)

        // firebase
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString().trim()

            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // show progress
            pbLogin.visibility = View.VISIBLE
            btnLogin.isEnabled = false

            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    pbLogin.visibility = View.GONE
                    btnLogin.isEnabled = true
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Login failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }

        tvSignUp.setOnClickListener {

            val i = Intent(this, RegisterActivity::class.java)
            i.putExtra("register", true)
            startActivity(i)
            finish()
        }

        tvContinueAdmin.setOnClickListener {
            startActivity(Intent(this, AdminLoginActivity::class.java))
        }

        tvContinueGuest.setOnClickListener {
            val i = Intent(this, HomeActivity::class.java)
            i.putExtra("guest", true)
            startActivity(i)
            finish()
        }

        tvForgot.setOnClickListener {
            val email = etEmail.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter your email to reset password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            pbLogin.visibility = View.VISIBLE
            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                pbLogin.visibility = View.GONE
                if (task.isSuccessful) {
                    Toast.makeText(this, "Password reset email sent", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}