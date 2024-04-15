package com.matheus.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.matheus.firstapp.databinding.FragmentPessoaBinding
import com.matheus.firstapp.service.model.Pessoa
import com.matheus.firstapp.viewmodel.PessoaViewModel


class PessoaFragment : Fragment(){
    private val viewModel: PessoaViewModel by viewModels()

    private var _binding : FragmentPessoaBinding? = null
    private val binding : FragmentPessoaBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPessoaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnviar.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()
            var anoNascimento = binding.edtNascimento.editableText.toString()
            var faixa = ""
            var sexo = ""

            if (nome != "" && anoNascimento != ""){

                if (binding.rbMasculino.isChecked){
                    sexo = "Masculino"
                } else{
                    sexo = "Feminino"
                }

//            val anoAtual = LocalDateTime.now().year
                var idade = 2024 - anoNascimento.toInt()

                // Faixa Etária
                if (idade <= 12){
                    faixa = "Criança"
                } else if (idade <= 17){
                    faixa = "Adolescente"
                } else if (idade <= 64){
                    faixa = "Adulto"
                } else{
                    faixa = "Idoso"
                }

                val pessoa = Pessoa(
                    nome = nome,
                    idade = idade,
                    faixa = faixa,
                    sexo = sexo
                )

                viewModel.insert(pessoa)

                binding.edtNome.editableText.clear()
                binding.edtNascimento.editableText.clear()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "DIGITA AI", Toast.LENGTH_LONG).show()
            }
        }
    }
}


