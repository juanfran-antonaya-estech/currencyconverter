package com.juanfran.currencyconverter

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import com.juanfran.currencyconverter.databinding.FragmentFragmentoConversorBinding

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
            val buildir = AlertDialog.Builder(this)
            buildir.setTitle("Mensaje del sistema")
            buildir.setMessage("¿Deseas enviar esta conversión?")
            buildir.setNegativeButton("No",null)
            buildir.setPositiveButton("Sí", DialogInterface.OnClickListener{ dialog, i ->
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
}