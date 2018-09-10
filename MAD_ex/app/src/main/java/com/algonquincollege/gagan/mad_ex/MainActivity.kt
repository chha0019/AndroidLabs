package com.algonquincollege.gagan.mad_ex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var aNumber = 10
        if(aNumber < 20){
            Log.i("output", " The number is less than 20")
        }
            else
            {
                Log.i("Output","The number is greater than 20")
            }
        val aList = listOf("first", "Second", "Third")
        for(index in 0..aList.size-1)
        {
            Log.i("Index", ""+index)
        }
        }
    }

