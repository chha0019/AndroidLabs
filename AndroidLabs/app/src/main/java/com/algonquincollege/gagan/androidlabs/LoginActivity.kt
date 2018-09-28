package com.algonquincollege.gagan.androidlabs

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_login.*
import java.io.File

class LoginActivity : Activity() {

    val activityName = "START_ACTIVITY"
    val RESULT_CODE =1
    val text = "Switch is on"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.i(activityName,"In onCreate")
        val prefs = getSharedPreferences("userData", Context.MODE_PRIVATE)
        val editText1 = findViewById<EditText>(R.id.editText1)
        val userName = prefs.getString("defaultEmail","email@domain.com")
        editText1.setText(userName)
        Log.i("Email","Found is" + userName)


        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton?.setOnClickListener(View.OnClickListener {
            val editor = prefs.edit()
            val name = editText1.getText().toString()
            editor.putString("defaultEmail", name)
            editor.commit()
            val newActivity = Intent(this, StartActivity::class.java)
            startActivityForResult(newActivity,50)
            Log.i("My Application", "Shared Preference saved")

        })



    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode,data)
        if (requestCode == Activity.RESULT_OK) {
            if (resultCode == RESULT_CODE) {
                Log.i(activityName, "Returned to StartActivity.onActivityResult")
            }
        }
        else
            Log.e(activityName,"Activity not found")
    }

    override fun onResume() {
        super.onResume()
    Log.i(activityName, "In onResume")
    }

    override fun onPause(){
        super.onPause()
        Log.i(activityName,"In onPause")
    }
    override fun onStart() {
        super.onStart()
        Log.i(activityName,"In onStart")
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
