package com.example.sirat_e_mustaqeem

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AdminLoginActivity : AppCompatActivity() {

    private lateinit var etAdminEmail: EditText
    private lateinit var etAdminPassword: EditText
    private lateinit var btnAdminLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        etAdminEmail = findViewById(R.id.etAdminEmail)
        etAdminPassword = findViewById(R.id.etAdminPassword)
        btnAdminLogin = findViewById(R.id.btnAdminLogin)

        btnAdminLogin.setOnClickListener {
            val email = etAdminEmail.text.toString().trim()
            val pass = etAdminPassword.text.toString().trim()

            // Simple admin check (you can later secure it better)
            if (email == "admin@sirat.com" && pass == "admin123") {
                startActivity(Intent(this, AdminDashboardActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid admin credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
