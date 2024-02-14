package com.juanfran.currencyconverter

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.juanfran.currencyconverter.databinding.FragmentFragmentoConversorBinding
import java.util.stream.IntStream
import java.util.stream.IntStream.range

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

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


private lateinit var binding: FragmentFragmentoConversorBinding
class FragmentoConversor : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragmentoConversorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        }




    }

    private fun getRelacion(): Double {
        val origen = obtenerNumero(binding.spOrigen.selectedItem.toString())
        val destino = obtenerNumero(binding.spDestino.selectedItem.toString())
        val cantidad = binding.etInput.text.toString().toDouble()

        return (origen * cantidad / destino)
    }

    private fun obtenerNumero(texto: String): Double {
        for (i in range(0, moneas[0].size)) {
            if (i == moneas[0][i]){
                return moneas[0][1].toString().toDouble()
            }
        }

        return 0.0
    }

    private fun obtenerDrawable(texto: String): Drawable? {
        for (i in range(0, moneas[0].size)) {
            if (i == moneas[0][i]){
                return getResources().getDrawable(moneas[2][i].toString().toInt())
            }
        }


        return null
    }
}