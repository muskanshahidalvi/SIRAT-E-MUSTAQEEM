package com.example.sirat_e_mustaqeem

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcomeHome)
        val tvSub = findViewById<TextView>(R.id.tvSubHome)
        val tvGuestInfo = findViewById<TextView>(R.id.tvGuestInfo)
        val btnDonateNow = findViewById<Button>(R.id.btnDonateNow)
        val btnMyDonations = findViewById<Button>(R.id.btnMyDonations)

        val isGuest = intent.getBooleanExtra("guest", false)
        if (isGuest) {
            tvWelcome.text = "Welcome, Guest!"
            tvGuestInfo.visibility = android.view.View.VISIBLE
            btnMyDonations.isEnabled = false
        } else {
            // If user is logged in, you can display user email or name later
            tvWelcome.text = "Welcome!"
            tvGuestInfo.visibility = android.view.View.GONE
            btnMyDonations.isEnabled = true
        }

        btnDonateNow.setOnClickListener {
            // navigate to campaign list or donate flow (to implement)
        }
    }
}

