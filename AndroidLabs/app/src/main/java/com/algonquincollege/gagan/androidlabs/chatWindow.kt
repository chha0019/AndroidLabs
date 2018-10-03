package com.algonquincollege.gagan.androidlabs

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlin.collections.ArrayList
import kotlin.math.log

class chatWindow : Activity() {

    val arrayList= ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_window)

        val textButton = findViewById<Button>(R.id.sendButton)
        val chatText = findViewById<EditText>(R.id.chatText)
        val listView = findViewById<ListView>(R.id.chatView)

        val messageAdapter = chatAdapter(this);
        Log.i(messageAdapter.toString(),"  hshdjhs")
        listView.setAdapter(messageAdapter);

//        textButton.setOnClickListener(View.OnClickListener {
//            arrayList.add(chatText.getText().toString())
//        Log.i(arrayList.toString(), " is in the arraylist")
//
//        })

        textButton.setOnClickListener(View.OnClickListener {

            arrayList.add(chatText.getText().toString())
            messageAdapter.notifyDataSetChanged()
            chatText.setText("");

        })
    }

        inner class chatAdapter(context:Context) : ArrayAdapter<String>(context,0) {

            override fun getCount(): Int {
                return arrayList.size
            }

            override fun getItem(position: Int): String {
                return arrayList.get(position)
            }

            override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
                val inflater = LayoutInflater.from(parent?.getContext())
                var result = null as View?
                if(position%2== 0) {
                    result = inflater.inflate(R.layout.chat_row_incoming,null)
                }
                else
                {
                    result = inflater.inflate(R.layout.chat_row_ongoing, null)
                }

                val message = result?.findViewById<TextView>(R.id.message_text)

                message?.setText( getItem(position) ) // get the string at position

                return result

            }

            override fun getItemId(position: Int): Long {
                return super.getItemId(position)
            }
            }

}



