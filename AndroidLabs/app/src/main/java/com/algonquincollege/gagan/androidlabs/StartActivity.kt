package com.algonquincollege.gagan.androidlabs

import android.app.Activity
import android.app.ListActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class StartActivity : Activity() {

    val activityName = "START_ACTIVITY"
    val RESULT_CODE =1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        Log.i(activityName, "In onCreate()");
        //val chatButton = findViewById<Button>(R.id.chatButton)
        val chatButton = findViewById<Button>(R.id.chatButton)
        val button2 = findViewById<Button>(R.id.button)

        button2?.setOnClickListener(View.OnClickListener {
           val anotherActivity = Intent(this, ListItemActivity::class.java)
            startActivityForResult(anotherActivity,50)
        })

        chatButton?.setOnClickListener(View.OnClickListener {
            Log.i(activityName,"User clicked start chat")
        })

        chatButton.setOnClickListener(View.OnClickListener {
            //    arrayList.add(chatText.)
            val newActivity = Intent(this,chatWindow::class.java)
            startActivityForResult(newActivity,50)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode,data)
        if (requestCode==50) {
            if (resultCode == Activity.RESULT_OK) {
                Log.i(activityName, "Returned to ListActivity.onActivityResult")
                val messagePassed = data?.getStringExtra("Response")
                Toast.makeText(this,messagePassed,Toast.LENGTH_LONG).show()
            }
        }
        else
            Log.e(activityName,"Activity not found")
    }



    override fun onResume() {
        super.onResume()
        Log.i(activityName,"In onResume")

    }

    override fun onStart() {
        super.onStart()
        Log.i(activityName,"In onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i(activityName,"In onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(activityName,"In onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(activityName,"In onDestroy")
    }

}
