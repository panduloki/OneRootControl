package com.example.onerootcontrol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SessionActivity : AppCompatActivity() {

    private lateinit var sessionRecyclerView: RecyclerView
    private lateinit var sessionArrayList: ArrayList<User>


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


    private fun getUserSessionData()
    {
        val db = Firebase.firestore

        db.collection("session")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val session = document.toObject(User::class.java)
                    sessionArrayList.add(session)
                }

                // crated an adapter for user item click
                val adapter = MyAdapter(sessionArrayList)
                sessionRecyclerView.adapter= adapter
            }
            .addOnFailureListener { exception ->
                Log.w(MainActivity.TAG, "Error getting documents: ", exception)
            }
    }
}
