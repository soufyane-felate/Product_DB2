package com.example.felatesoufyanedb001

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBase(context:Context): SQLiteOpenHelper(context,"DB_Product",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS info(id INTEGER PRIMARY KEY ,name TEXT, price REAL ,image TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS info")
        onCreate(db)

    }
    fun addData (id:Int,name:String,price:Double,image:String):Long{
        val writableDb=this.writableDatabase
        val contextValue=ContentValues().apply {
            put("id",id)
            put("name",name)
            put("price",price)
            put("image",image)
        }
        return writableDb.insert("info",null,contextValue)
    }
    @SuppressLint("Range")
    fun getAllProducts(): List<Product> {
        val productList = mutableListOf<Product>()
        val readableDb = this.readableDatabase
        val cursor = readableDb.rawQuery("SELECT * FROM info", null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val price = cursor.getDouble(cursor.getColumnIndex("price"))
            val image = cursor.getString(cursor.getColumnIndex("image"))

            val product = Product(id, name, price, image)
            productList.add(product)
        }

        cursor.close()
        return productList
    }

}