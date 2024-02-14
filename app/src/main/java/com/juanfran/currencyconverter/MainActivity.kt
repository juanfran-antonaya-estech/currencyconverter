package com.juanfran.currencyconverter

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import java.util.stream.IntStream.range

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentoConversor = FragmentoConversor()
        val gestorDeFragment = supportFragmentManager
        val transaccion = gestorDeFragment.beginTransaction()
        transaccion.add(R.id.container, fragmentoConversor)
        transaccion.commit()
    }

}