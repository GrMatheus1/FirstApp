package com.matheus.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.matheus.firstapp.R
import com.matheus.firstapp.databinding.FragmentAllPessoasBinding
import com.matheus.firstapp.databinding.FragmentPessoaBinding
import com.matheus.firstapp.databinding.FragmentPessoaDetailBinding
import com.matheus.firstapp.service.model.Pessoa
import com.matheus.firstapp.viewmodel.PessoaViewModel
import java.time.LocalDateTime


class PessoaDetailFragment : Fragment() {

    //Chamar a viewmodel para pegar os dados
    private val viewModel: PessoaViewModel by viewModels()

    //Criar o binding para pegar os elementos da tela
    private var _binding: FragmentPessoaDetailBinding? = null
    private val binding: FragmentPessoaDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Configurar o binding
        _binding = FragmentPessoaDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

//Chamar a função OnViewCreated onde vamos implementar o código
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Pegar o Id da pessoa que foi enviado pela AllPessoasFragment
        //Setar a pessoa para ser carregada
        arguments?.let {
            viewModel.getPessoa(it.getInt("pessoaId"))
        }

        //Carregar as informações da pessoa selecionada
        viewModel.pessoa.observe(viewLifecycleOwner) { pessoa ->
            binding.tvDetalheNome.text = pessoa.nome
            binding.tvDetalheIdade.text = pessoa.idade.toString()
            binding.tvDetalheFaixa.text = pessoa.faixa
            if (pessoa.sexo == "Masculino") {
                binding.imgDetalheMasculino.visibility = View.VISIBLE
                binding.imgDetalheFeminino.visibility = View.GONE
            } else {
                binding.imgDetalheMasculino.visibility = View.GONE
                binding.imgDetalheFeminino.visibility = View.VISIBLE
            }

        }
    }
}
