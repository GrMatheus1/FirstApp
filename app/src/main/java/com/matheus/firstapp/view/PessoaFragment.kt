package com.matheus.firstapp.view

import android.app.AlertDialog
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
import java.time.LocalDateTime


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

        //Carregar a pessoa caso tenha selecionado
        arguments?.let {
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        binding.btnEnviar.setOnClickListener {
            var nome = binding.edtNome.editableText.toString()
            var anoNascimento = binding.edtNascimento.editableText.toString()
            var faixa = ""
            var sexo = ""

            if (nome != "" && anoNascimento != "" &&
                binding.rbMasculino.isChecked || binding.rbFeminino.isChecked){

                if (binding.rbMasculino.isChecked){
                    sexo = "Masculino"
                } else{
                    sexo = "Feminino"
                }

//            val anoAtual = LocalDateTime.now().year
                var idade = 2024 - anoNascimento.toInt()

                // Faixa Etária
                if (idade <= 12){
                    faixa = "Infantil"
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
                viewModel.pessoa.value?.let {
                    pessoa.id = it.id
                    viewModel.update(pessoa)
                }?:run{
                    viewModel.insert(pessoa)
                }


                binding.edtNome.editableText.clear()
                binding.edtNascimento.editableText.clear()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "DIGITA AI", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnDeletar.setOnClickListener {
            viewModel.delete(viewModel.pessoa.value?.id ?:0)
            findNavController().navigateUp()
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Exclusão de pessoa")
            .setMessage("Você deseja excluir?")
            .setPositiveButton("Sim"){_,_ ->
                viewModel.delete(viewModel.pessoa.value?.id ?:0)
                findNavController().navigateUp()
            }
            .setNegativeButton("Não"){_,_ ->}
            .show()

        viewModel.pessoa.observe(viewLifecycleOwner){pessoa ->
            binding.edtNome.setText(pessoa.nome)
            binding.edtNascimento.setText((LocalDateTime.now().year - pessoa.idade).toString())

            if (pessoa.sexo == "Masculino"){
                binding.rbMasculino.isChecked = true
            } else {
                binding.rbFeminino.isChecked = true
            }
            binding.btnDeletar.visibility = View.VISIBLE
        }
    }
}


