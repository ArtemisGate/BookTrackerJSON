package com.example.booktracker

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment


// Class extends DialogFrament() class
// Override default onCreateDialog function
// Class is called after Floating Action Button is pressed
class DialogNewEntry : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //Create a AlertDialog Builder Object passing in activity
        // Create layoutInflator to for inflating layout
        // Create View and inflate the layout to this object
        var builder1 = AlertDialog.Builder(activity!!)
        var inflater1 = activity!!.layoutInflater
        var newEntryView = inflater1.inflate(R.layout.dialog_new_entry,null)

        // Get UI elements object created using findViewById
        var name = newEntryView.findViewById<EditText>(R.id.dialog_new_name)
        var author = newEntryView.findViewById<EditText>(R.id.dialog_new_author)
        var okBtt = newEntryView.findViewById<Button>(R.id.dialog_new_button)


        // Set AlertDialog Builder View
        builder1.setView(newEntryView)
            .setMessage("Enter Book Name and Author")

        // Set on button click behavior
        okBtt.setOnClickListener {
            // Create new book object and get User input
            var newEntry = Book()
            newEntry.title = name.text.toString()
            newEntry.author = author.text.toString()




            // Get a reference to MainActivity
            val callingActivity = activity as MainActivity?

            // Pass newNote back to MainActivity
            callingActivity!!.createNewEntry(newEntry)

            dismiss()
        }

        // Create the AlertDialog Builder object
        return builder1.create()

    }


}