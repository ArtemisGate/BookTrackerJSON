package com.example.booktracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


// Adapter class has to extend RecyclerView.
// Adapter<CLASS_NAME._CUSTOM_HOLDER>
// CUSTOM HOLDER has to extend RecyclerView.Viewholder
// Take mainActivity reference and List of Book reference
class BookAdapter(
        private val mainActivity : MainActivity,
        private val bookList : List<Book>
) : RecyclerView.Adapter<BookAdapter.ItemHolder>() {


    // Inner class that extends RecyclerView.Viewholder Class and
    // overrides onclick
    inner class ItemHolder(view2: View) : RecyclerView.ViewHolder(view2),View.OnClickListener {
        // Varialbes to hold values in view called list_item
        internal var mTitle =
                view2.findViewById<TextView>(R.id.recycler_view_title)

        internal var mAuthor = view2.findViewById<TextView>(R.id.recyclerView_author)

        init {
            view2.isClickable = true;
            view2.setOnClickListener(this)
        }

        override fun onClick(view : View) {
            mainActivity.showNote(adapterPosition)
        }


    }

    // Create and inflate view and returns Custom Viewholder object
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView5 = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)

        return ItemHolder(itemView5)



    }

    // Takes the view and bind data to it
    override fun onBindViewHolder(holder21: ItemHolder, position: Int) {


        val entry = bookList!![position]
        holder21.mTitle.text = entry.title
        holder21.mAuthor.text = entry.author



    }

    // return number of items in list
    override fun getItemCount(): Int {
        return bookList.size
    }
}