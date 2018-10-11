package com.algonquincollege.gagan.androidlabs

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlin.collections.ArrayList
import kotlin.math.log

class chatWindow : Activity() {

    val DATABASE_NAME = "Mydb"
    val VERSION_NO = 3
    val arrayList= ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_window)

        val dbHelper = ChatDatabaseHelper()  //this will open database
        val db = dbHelper.writableDatabase   //Now we can insert into here

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
            var userTyped = chatText.getText().toString()
            arrayList.add(userTyped)
            messageAdapter.notifyDataSetChanged()
            chatText.setText("");

            //add to database
            var newRow = ContentValues()
            newRow.put(dbHelper.KEY_MESSAGE,userTyped)
            db.insert(dbHelper.TABLE_NAME,"",newRow)  //Insert new row
        })


        //Run a query
        var results = db.query(dbHelper.TABLE_NAME,arrayOf(dbHelper.KEY_MESSAGE,dbHelper.KEY_ID),null,null, null, null, null, null)

        var numRows = results.getCount()  //NO of rows in Cursor Object

        results.moveToFirst()  //read from the first fow because cursor object always point to the current row

        val keyIndex = results.getColumnIndex(dbHelper.KEY_MESSAGE)  //find out the coloumn no for messages field

        while(!results.isAfterLast){

            var thisMessage = results.getString(keyIndex)

            arrayList.add(thisMessage) //Pre load messages from list
            results.moveToNext()
        }
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

    inner class ChatDatabaseHelper : SQLiteOpenHelper(this@chatWindow,DATABASE_NAME,null,VERSION_NO){
        val KEY_ID = "_id"
        val TABLE_NAME = "CHAT_MESSAGES"
        val KEY_MESSAGE = "Messages"
        override fun onCreate(db: SQLiteDatabase) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

            db.execSQL("CREATE TABLE " + TABLE_NAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_MESSAGE +" TEXT)")

        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)  //delete all current table
            onCreate(db)  //create new table
        }

    }
}



