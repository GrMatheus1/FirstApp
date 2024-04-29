package com.matheus.firstapp.view.adapter

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.matheus.firstapp.R
import com.matheus.firstapp.databinding.ListItemPessoaBinding
import com.matheus.firstapp.service.model.Pessoa

class PessoaAdapter(pessoas: List<Pessoa>?, private val clickListListener: (Pessoa) -> Unit):
    RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>(){

        //Criar uma lista vazia de pessoas
        private var pessoaList: List<Pessoa> = arrayListOf()

    class PessoaViewHolder (private val binding: ListItemPessoaBinding):
            RecyclerView.ViewHolder(binding.root){

                //Carrega as informações da pessoa na lista
                fun bind(pessoa: Pessoa, clickListListener: (Pessoa) -> Unit ){
                    binding.tvNome.text = pessoa.nome
                    binding.tvIdade.text = pessoa.idade.toString()
                    binding.tvFaixa.text = pessoa.faixa

                    if (pessoa.sexo == "Masculino" ){
                        binding.imgMasculino.visibility = View.VISIBLE
                        binding.imgFeminino.visibility = View.GONE
                    } else{
                        binding.imgMasculino.visibility = View.GONE
                        binding.imgFeminino.visibility = View.VISIBLE
                    }

                    //Configura o click de algum item da lista
                    binding.root.setOnClickListener{
                        clickListListener(pessoa)
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val listItemPessoaBinding =
            ListItemPessoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PessoaViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        return pessoaList.count()
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bind(pessoaList[position], clickListListener)
    }

    fun updatePessoas(list: List<Pessoa>){
        pessoaList = list
        notifyDataSetChanged()
    }
}