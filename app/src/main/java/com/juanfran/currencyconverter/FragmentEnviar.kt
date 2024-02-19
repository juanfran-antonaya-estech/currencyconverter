package com.juanfran.currencyconverter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.setType("text/html")
            intent.putExtra(Intent.EXTRA_EMAIL, mail)
            intent.putExtra(Intent.EXTRA_SUBJECT, "La conversión")
            intent.putExtra(Intent.EXTRA_TEXT, param1)
            startActivity(intent)
        }

        //TODO: No va
        binding.fabMensaje.setOnClickListener{
            val sms : SmsManager = SmsManager.getDefault()
            sms.sendTextMessage(null,null,param1,null,null)
        }

        binding.fabInstagram.setOnClickListener{
            val usuario = "lilcabezaog"
            val uri = Uri.parse("http://instagram.com/_u/$usuario")

            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.setPackage("com.instagram.android")
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/$usuario"))
                )
            }
        }

        binding.fabX.setOnClickListener{
            val usuario = "La_Fresadora"

            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("twitter://user?screen_name=$usuario")
                    )
                )
            } catch (e: Exception) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://twitter.com/#!/$usuario")
                    )
                )
            }
        }
    }
}