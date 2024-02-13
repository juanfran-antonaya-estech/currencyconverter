package com.juanfran.currencyconverter

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import androidx.appcompat.app.AppCompatActivity
import com.juanfran.currencyconverter.databinding.EnviarCosasBinding


private lateinit var binding: EnviarCosasBinding

class EnvialCosas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = EnviarCosasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val b = intent.extras
        val conversion = b?.getString("conversion")

        binding.btEnviar.setOnClickListener{
            val mail = binding.etEmail.text.toString()

            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.setType("text/html")
            intent.putExtra(Intent.EXTRA_EMAIL, mail)
            intent.putExtra(Intent.EXTRA_SUBJECT, "La conversión")
            intent.putExtra(Intent.EXTRA_TEXT, conversion)
            startActivity(intent)
        }

        binding.fabMail.setOnClickListener{
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.setType("text/html")
            intent.putExtra(Intent.EXTRA_SUBJECT, "La conversión")
            intent.putExtra(Intent.EXTRA_TEXT, conversion)
            startActivity(intent)
        }

        //TODO: No va
        binding.fabMensaje.setOnClickListener{
            val sms : SmsManager = SmsManager.getDefault()
            sms.sendTextMessage(null,null,conversion,null,null)
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