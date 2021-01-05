package com.example.booktracker

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import org.w3c.dom.Text

// Class extends DialogFragment Class
// Called when user press entry in RecyclerView
class DialogShowEntry : DialogFragment() {

    // Object to hold book reference
    private var book : Book? = null

    // Take in book reference and set interal book object to this reference
    fun sendEntry(entrySelect : Book)
    {
        book = entrySelect
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Create object of AlertDialog.Builder, layoutinflator
        // Inflate layout and create new view out of it
        var builder1 = AlertDialog.Builder(activity!!)
        var inflater1 = activity!!.layoutInflater
        var newEntryView = inflater1.inflate(R.layout.dialog_show_entry,null)


        // UI element objects in layout
        var name = newEntryView.findViewById< TextView>(R.id.dialog_show_title)
        var author = newEntryView.findViewById<TextView>(R.id.dialog_show_author)
        var okBtt = newEntryView.findViewById<Button>(R.id.dialog_show_button)

        // Assign value of book to Dialog Show Entry TextFields
        name.text = book!!.title
        author.text = book!!.author

        // Set view
        builder1.setView(newEntryView)

        // Dismiss if done
        okBtt.setOnClickListener{
            dismiss()
        }

        // Create the builder
        return builder1.create()
    }





}