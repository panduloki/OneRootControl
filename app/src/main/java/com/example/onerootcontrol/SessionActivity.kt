package com.example.onerootcontrol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SessionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        dispatchTakeHomeIntent()
    }

    private fun dispatchTakeHomeIntent() {
        val intent = Intent(this, MainActivity::class.java)
        println("home activity closed")
        startActivity(intent)
        this.finish()
    }
}
