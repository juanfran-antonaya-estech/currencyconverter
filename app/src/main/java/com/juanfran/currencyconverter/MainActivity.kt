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
import com.juanfran.currencyconverter.databinding.ActivityMainBinding
import java.util.stream.IntStream.range

private lateinit var binding: ActivityMainBinding

private val moneas = arrayOf(
    arrayOf<String>(
        "Rupia",
        "Chapas",
        "Euro",
        "Creditos",
        "Oro WOW"
    ),
    arrayOf<Double>(
        0.18,
        0.08,
        1.00,
        16.00,
        25898.8
    ),
    arrayOf<Int>(
        R.drawable.rupia,
        R.drawable.chapas,
        R.drawable.euro,
        R.drawable.creditos,
        R.drawable.orowow
    )
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.spOrigen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            // funcion llamada cuando se selecciona un elemento del spinner
                binding.ivOrigen.setImageDrawable(obtenerDrawable(binding.spOrigen.selectedItem.toString()))
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            // funcion llamada cuando no se selecciona nada del spinner
            }
        }
        binding.spDestino.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            // funcion llamada cuando se selecciona un elemento del spinner
                binding.ivDestino.setImageDrawable(obtenerDrawable(binding.spDestino.selectedItem.toString()))
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            // funcion llamada cuando no se selecciona nada del spinner
            }
        }

        binding.btConvert.setOnClickListener{
            binding.tvResult.text = String.format("%.2f %s", getRelacion(), binding.spDestino.selectedItem.toString())
        }

        binding.fabSend.setOnClickListener{
            val buildir = AlertDialog.Builder(this)
            buildir.setTitle("Mensaje del sistema")
            buildir.setMessage("¿Deseas enviar esta conversión?")
            buildir.setNegativeButton("No",null)
            buildir.setPositiveButton("Sí", DialogInterface.OnClickListener{dialog, i ->
                val conversion = binding.tvResult.text.toString()
                val intent = Intent(this, EnvialCosas::class.java)
                intent.action = Intent.ACTION_SEND
                intent.putExtra("conversion", conversion)
                intent.setType("text/plain")
                startActivity(intent)

            })
            val dialog = buildir.create()
            dialog.show()
        }
    }

    private fun getRelacion(): Double {
        val cantidad = binding.etInput.text.toString().toDouble()
        val origen = obtenerElNumero(binding.spOrigen.selectedItem.toString())
        val destino = obtenerElNumero(binding.spDestino.selectedItem.toString())
        val resultado = cantidad * destino!! / origen!!
        return resultado
    }

    private fun obtenerElNumero(string: String): Double? {
        for (i in range(0,moneas[0].size)) {
            if(moneas[0][i] == string) {
                return moneas[1][i].toString().toDouble()
            }
        }
        return null
    }

    private fun obtenerDrawable(text: String): Drawable? {

        for (i in range(0,moneas[0].size)) {
            if(moneas[0][i] == text) {
                return getDrawable(moneas[2][i].toString().toInt())
            }
        }
        return null
    }
}