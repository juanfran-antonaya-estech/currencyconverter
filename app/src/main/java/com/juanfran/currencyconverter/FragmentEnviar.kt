package com.juanfran.currencyconverter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat.IntentBuilder
import com.juanfran.currencyconverter.databinding.FragmentEnviarBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentEnviar.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentEnviar : Fragment() {
    private lateinit var binding: FragmentEnviarBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnviarBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val param1 = arguments?.getString("conversion")
        binding.textView4.text = param1

        binding.btEnviar.setOnClickListener{
            val mail = binding.etEmail.text
            val result = param1


        }
    }
}