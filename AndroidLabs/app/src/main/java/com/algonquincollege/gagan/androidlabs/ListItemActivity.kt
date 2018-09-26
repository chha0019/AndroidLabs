package com.algonquincollege.gagan.androidlabs

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.AlertDialogLayout
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Switch
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_item.*

class ListItemActivity : Activity() {
val REQUEST_IMAGE_CAPTURE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_item)
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val switch = findViewById<Switch>(R.id.switch1)
        val imageButton = findViewById<ImageButton>(R.id.imageButton)
       // val checkBox = findViewById<CheckBox>(R.id.checkBox)

imageButton?.setOnClickListener(View.OnClickListener {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
        takePictureIntent.resolveActivity(packageManager)?.also {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }
})
        switch?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                Toast.makeText(this,isChecked.toString(), Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this,isChecked.toString(), Toast.LENGTH_LONG).show()

            }
        }
        checkBox?.setOnCheckedChangeListener { buttonView, isChecked ->
        if (isChecked)
        {
            val builder = AlertDialog.Builder(this)
            builder.setMessage(R.string.dialouge_msg).setTitle(R.string.dialouge_title).setPositiveButton(R.string.OK,{dialog, id ->
                val resultIntent = Intent( )

                resultIntent.putExtra("Response", "Here is my response")

                setResult(Activity.RESULT_OK, resultIntent)

                finish()
            }).setNegativeButton(R.string.Cancel,{dialog, id ->
                val resultIntent = Intent( )

//                resultIntent.putExtra("Response", "Here is my response")
//
//                setResult(Activity.RESULT_CANCELED, resultIntent)
//
//                finish()
            }).show()
        }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data.extras.get("data") as Bitmap
            imageButton.setImageBitmap(imageBitmap)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}
