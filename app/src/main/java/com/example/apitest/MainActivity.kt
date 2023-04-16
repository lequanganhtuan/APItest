package com.example.apitest

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        replaceFragment(MovieFragment())
    }

    private fun replaceFragment(movieFragment: Fragment) {
        val  fragmentManager = supportFragmentManager
        val framentTransaction = fragmentManager.beginTransaction()
        framentTransaction.replace(R.id.frame_layout, movieFragment)
        framentTransaction.commit()
    }
}