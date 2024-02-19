package com.juanfran.currencyconverter

import android.content.res.Resources
import android.content.res.loader.ResourcesProvider
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.juanfran.currencyconverter.databinding.FragmentFragmentoConversorBinding
import java.util.stream.IntStream.range

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER





class FragmentoConversor : Fragment() {
    public val monedas = arrayOf(
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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFragmentoConversorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.spOrigen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // funcion llamada cuando se selecciona un elemento del spinner
                binding.ivOrigen.setImageResource(obtenerDrawable(binding.spOrigen.selectedItem.toString()))
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // funcion llamada cuando no se selecciona nada del spinner
            }
        }
        binding.spDestino.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // funcion llamada cuando se selecciona un elemento del spinner
                binding.ivDestino.setImageResource(obtenerDrawable(binding.spDestino.selectedItem.toString()))
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // funcion llamada cuando no se selecciona nada del spinner
            }
        }

        binding.btConvert.setOnClickListener{
            val r = String.format("%.2f %s", getRelacion(), binding.spDestino.selectedItem.toString())
            binding.tvResult.text = r
        }

        binding.fabSend.setOnClickListener{
            val bundle = bundleOf("conversion" to binding.tvResult.text)
            findNavController().navigate(R.id.action_fragmentoConversor_to_fragmentEnviar, bundle)
        }




    }

    private fun getRelacion(): Double {
        val origen = obtenerNumero(binding.spOrigen.selectedItem.toString())
        val destino = obtenerNumero(binding.spDestino.selectedItem.toString())
        val cantidad = binding.etInput.text.toString().toDouble()

        return (origen * cantidad) / destino
    }

    private fun obtenerNumero(texto: String): Double {
        for (i in range(0, monedas[0].size)) {
            if (texto == monedas[0][i].toString()){
                return monedas[1][i].toString().toDouble()
            }
        }

        return 0.0
    }

    private fun obtenerDrawable(texto: String): Int {
        for (i in range(0, monedas[0].size)) {
            if (texto == monedas[0][i].toString()){
                val a = monedas[2][i].toString().toInt()
                return a
            }
        }


        return 0
    }
}