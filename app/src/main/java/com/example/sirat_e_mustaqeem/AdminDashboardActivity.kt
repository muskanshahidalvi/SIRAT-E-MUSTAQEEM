package com.example.sirat_e_mustaqeem

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var cardManageUsers: LinearLayout
    private lateinit var cardManageCampaigns: LinearLayout
    private lateinit var cardViewDonations: LinearLayout
    private lateinit var cardLogout: LinearLayout
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        cardManageUsers = findViewById(R.id.cardManageUsers)
        cardManageCampaigns = findViewById(R.id.cardManageCampaigns)
        cardViewDonations = findViewById(R.id.cardViewDonations)
        cardLogout = findViewById(R.id.cardLogout)
        auth = FirebaseAuth.getInstance()



        cardLogout.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
