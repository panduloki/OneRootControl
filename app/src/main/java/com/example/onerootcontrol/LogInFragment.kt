package com.example.onerootcontrol

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment


class LogInFragment : Fragment() {
    private var  authorityRole = "None"
    private val userName1 = "C.E.O"
    private val userName2 = "Back End Developer"
    private val password1 = "oneroot.ceo"
    private val password2 = "oneroot.b"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_log_in,container,false)
        // get reference to all views
        //<---------------------------- role auto complete ---------------------------->
        val roleArray = resources.getStringArray(R.array.role_list)
        val arrayAdapter2 = activity?.let { ArrayAdapter(it, R.layout.dropdown_item, roleArray) }
        val autocompleteTV2 = view?.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView2)
        autocompleteTV2?.setAdapter(arrayAdapter2)
        autocompleteTV2?.setOnItemClickListener { _, _, _, _ ->
            authorityRole = autocompleteTV2.text.toString()
            println("authorityRole: $authorityRole")
        }
        //<---------------------------------------------------------------------------------->

        val passText = view.findViewById<EditText>(R.id.password)
        val signInBtn = view.findViewById<Button>(R.id.signInButton)
        val signInCheck = view.findViewById<CheckBox>(R.id.signInCheck)

        //shared preferences
        val sharedPref = activity?.getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()

        signInBtn?.setOnClickListener {
            val sgPassword = passText?.text.toString()
            val autoSignIn = signInCheck?.isChecked

            // your code to validate the user_name and password combination
            // and verify the same
            if (((authorityRole == userName1) && (sgPassword == password1))or ((authorityRole == userName2) && (sgPassword == password2))) {
                Toast.makeText(activity,"signed in", Toast.LENGTH_LONG).show()
                editor?.apply {
                    if (autoSignIn == true) {
                        putBoolean("autoSignIn", true)
                    }
                    else
                    {
                        putBoolean("autoSignIn", false)
                    }
                    putBoolean("signedIn",true)

                    apply()
                }
                // go to main fragment
                replaceFragment(HomeFragment())

            }
            else{
                editor?.apply {
                    putBoolean("signedIn",false)
                    putBoolean("autoSignIn",false)
                    apply()
                }
                Toast.makeText(activity, "try again", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }


//    private fun returnFragmentBack() {
//        activity?.supportFragmentManager?.popBackStack()
//
//    }
    private fun replaceFragment(fragment : Fragment){

        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frame_layout,fragment)
        fragmentTransaction?.commit()

    }

}