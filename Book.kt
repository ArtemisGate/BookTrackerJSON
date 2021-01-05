package com.example.booktracker

import org.json.JSONException
import org.json.JSONObject


// Class to store book data
class Book {
    var title: String? = null
    var author: String? = null

    private val JSON_TITLE = "title"
    private val JSON_AUTHOR = "author"

    @Throws(JSONException::class)
    constructor(jo: JSONObject) {
        title = jo.getString(JSON_TITLE)
        author = jo.getString((JSON_AUTHOR))
    }


    constructor()


    @Throws(JSONException::class)
    fun convertToJSON(): JSONObject {
        val jo = JSONObject()

        jo.put(JSON_TITLE, title)
        jo.put(JSON_AUTHOR, author)

        return jo
    }

}