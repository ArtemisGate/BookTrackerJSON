package com.example.booktracker

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    // Hold objects for BookList, RecyclerView and Custom Adapter
    private var mSerializer: JSONSerializer? = null
    private var entryList: ArrayList<Book>? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: BookAdapter? = null


    // Get a book object from DialogNewEntry and add book to list
    // Notify adapter that data has changed
    fun createNewEntry(newEntry: Book) {
        entryList!!.add(newEntry)
        adapter!!.notifyDataSetChanged()

    }

    override fun onPause() {
        super.onPause()

        saveEntry()
    }

    // Called when person click on entry in recyclerView
    // Create new Dialog ShowEntry object
    // Sends Book object at location parma adapterPosition
    fun showNote(adapterPosition: Int) {
        val dialog = DialogShowEntry()
        dialog.sendEntry(entryList!![adapterPosition])
        dialog.show(supportFragmentManager, "")
    }

    fun saveEntry() {
        try {
            mSerializer!!.save(this.entryList!!)
        } catch (e: Exception) {
            Log.e("Error saving", "", e)
        }

    }

    // Runs when app starts
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find Floating action button in layout and create object
        var fab = findViewById<FloatingActionButton>(R.id.main_fab)

        // When floating action button is clicked, create new Dialog
        fab.setOnClickListener {
            var dialog = DialogNewEntry()
            dialog.show(supportFragmentManager, "")
        }

        mSerializer = JSONSerializer("BookList.json", applicationContext)

        try {
            entryList = mSerializer!!.load()
        } catch (e: Exception) {
            entryList = ArrayList()
            Log.e("Error loading entry:", "", e)
        }

        // Assign recyclerView to id in layout
        recyclerView = findViewById<View>(R.id.main_recyclerView) as RecyclerView

        // Passes mainactivity and List of Books to adapter
        adapter = BookAdapter(this, entryList!!)
        val layoutManager = LinearLayoutManager(applicationContext)

        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        // Add a neat dividing line between items in the list
        recyclerView!!.addItemDecoration(
                DividerItemDecoration(this,
                        LinearLayoutManager.VERTICAL))

        // set the adapter
        recyclerView!!.adapter = adapter

    }


}