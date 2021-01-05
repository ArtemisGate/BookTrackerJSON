package com.example.booktracker

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONTokener
import java.io.*

class JSONSerializer(private val filename: String,
                     private val context: Context) {
    @Throws(IOException::class, JSONException::class)
    fun save(entry: List<Book>) {
        val jArray = JSONArray()

        for (n in entry) {
            jArray.put(n.convertToJSON())
        }

        var writer: Writer? = null

        try {
            val out = context.openFileOutput(filename, Context.MODE_PRIVATE)

            writer = OutputStreamWriter(out)

            writer.write((jArray.toString()))
        } finally {
            if (writer != null) {
                writer.close()
            }
        }

    }

    @Throws(IOException::class, JSONException::class)
    fun load(): ArrayList<Book> {
        var entryList = ArrayList<Book>()
        var reader: BufferedReader? = null

        try {
            val `in` = context.openFileInput(filename)
            reader = BufferedReader((InputStreamReader(`in`)))
            val jsonString = StringBuilder()

            for (line in reader.readLine()) {
                jsonString.append(line)
            }

            val jArray = JSONTokener(jsonString.toString()).nextValue()
                    as JSONArray

            for (i in 0 until jArray.length()) {
                entryList.add(Book(jArray.getJSONObject(i)))
            }
        } catch (e: FileNotFoundException) {
            // Can add log here
        } finally {
            //this will always run
            reader!!.close()
        }

        return entryList
    }
}