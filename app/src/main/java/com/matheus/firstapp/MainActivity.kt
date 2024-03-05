package com.matheus.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.matheus.firstapp.databinding.ActivityMainBinding
import com.matheus.firstapp.databinding.TelaLinearBinding
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding : ActivityMainBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

// tela_linear:

//        binding.btnEnviar2.setOnClickListener {
//            var nome = binding.edtNome.editableText.toString()
//
//            binding.tvNome.text = "Nome: ${nome}"
//
//            var anoNascimento = binding.edtIdade.editableText.toString()
//            val anoAtual = LocalDateTime.now().year
//            var idade = 2024 - anoNascimento.toInt()
//
//            binding.tvIdade.text = "Idade: ${idade}"
//        }

            binding.btnEnviar.setOnClickListener {
                var email = binding.edtEmail.editableText.toString()

                if (email.contains("@") && email.substringAfter("@").contains(".com")){
                    binding.tvEmail.text = "Email: ${email}"
                } else{
                    binding.tvEmail.text = "Email: Error"
                }

                var telefone = binding.edtTelefone.editableText.toString()


                if (telefone.length == 11){
                    binding.tvTelefone.text = "Telefone: ${telefone}"
                } else {
                    error("Não tem 11 digitos")
                }

            }
    }
}