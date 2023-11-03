package com.example.onerootcontrol

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.onerootcontrol.MainActivity.Companion.TAG

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// Recycler view
// https://www.youtube.com/watch?v=VVXKVFyYQdQ

// when recycler item clicked navigate to new view
// https://www.youtube.com/watch?v=WqrpcWXBz14
// https://www.youtube.com/watch?v=dB9JOsVx-yY
// https://www.youtube.com/watch?v=EoJX7h7lGxM
class HomeFragment : Fragment() {

    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        // inflate the layout and bind to the _binding
        val view = inflater.inflate(R.layout.fragment_home,container,false)
        userRecyclerView = view.findViewById(R.id.userRecyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(activity)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()
        getUserdata()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = this.activity?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        // val editor = sharedPref?.edit()
        val sessionStatus = sharedPref?.getInt("session",5)
        println("sessionStatus: $sessionStatus")
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//    }

//    private fun returnFragmentBack() {
//        activity?.supportFragmentManager?.popBackStack()
//
//    }

    private fun getUserdata()
    {
        val db = Firebase.firestore

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val users = document.toObject(User::class.java)
                    userArrayList.add(users)
                }

                // crated an adapter for user item click
                val adapter = MyAdapter(userArrayList)
                userRecyclerView.adapter= adapter

                // when user clicked
                adapter.setOnClickListener(object : MyAdapter.OnItemClickListener{
                    override fun onItemClick(position: Int) {
                        Toast.makeText(activity, "user clicked item no:$position", Toast.LENGTH_SHORT).show()
                        dispatchTakeSessionIntent()
                    }

                })
            }

            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }

    private fun dispatchTakeSessionIntent() {
        val intent = Intent(activity, SessionActivity::class.java)
        startActivity(intent)
        println("main activity closed")
        activity?.finish()
    }

}