package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var ItemList:ArrayList<item_list>
   private lateinit var RvtoDoList:RecyclerView
    private lateinit var rvadap:myAdapter
 lateinit var FlotAcBtn:FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  Initializing the UI elements.
        FlotAcBtn = findViewById(R.id.b1)
        ItemList = arrayListOf()
        RvtoDoList =findViewById(R.id.rv)
        rvadap = myAdapter(ItemList)
        RvtoDoList.adapter = rvadap
        RvtoDoList.layoutManager = LinearLayoutManager(this)

    // create OnClickListener and activate it so we can use it
        FlotAcBtn.setOnClickListener {
            customDailog()

        }


    }
    //  we use this function to the menu in the activity

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_list,menu)
        return true
    }
    // we use this function to show the items inside the menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var ItemsDel = 0
        for (i in ItemList){
            if (i.checked_list){ItemsDel++}
        }
        if(ItemsDel>0){
            Toast.makeText(this, "$ItemsDel  items delete it ", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "No Items Selected", Toast.LENGTH_SHORT).show()
        }
        rvadap.deleteList()
        return super.onOptionsItemSelected(item)
    }
    //  we use this function to show the dialog inside it we have the edit text and two buttons one is
    // PositiveButton and the second is NegativeButton

    private fun customDailog(){
        val dailogBuilder= AlertDialog.Builder(this)
        val layout = ConstraintLayout(this)
         var addItems = EditText(this)
        addItems.hint = " Enter Item"
        layout.addView(addItems)
        dailogBuilder.setPositiveButton("ADD", DialogInterface.OnClickListener{
            dailog,id ->ItemList.add(item_list(addItems.text.toString()))
            addItems.clearFocus()

        })
         .setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, id -> dialog.cancel()
         })
        // create a variable to hold the dialog


        val Alert = dailogBuilder.create()
        Alert.setTitle("New Item")
        Alert.setView(layout)
        Alert.show()
    }
    }
