package com.example.rastreadorcovid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pais_item.view.*

class PaisAdapter(paises:ArrayList<Pais>, contexto:Context):RecyclerView.Adapter<PaisAdapter.ViewHolder>() {
    var listaPaises:ArrayList<Pais>?=null
    var contexto:Context?=null

    init {
        this.listaPaises = paises
        this.contexto = contexto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisAdapter.ViewHolder {
        val vistaPais:View = LayoutInflater.from(contexto).inflate(R.layout.pais_item,parent, false)
        val paisViewHolder = ViewHolder(vistaPais)
        vistaPais.tag = paisViewHolder
        return paisViewHolder
    }

    override fun onBindViewHolder(holder: PaisAdapter.ViewHolder, position: Int) {
        holder.nombrePais!!.text = listaPaises!![position].nombre
        holder.numConfirmados!!.text = "${listaPaises!![position].confirmados}"
        holder.numMuertos!!.text = "${listaPaises!![position].muertos}"
        holder.numRecuperados!!.text = "${listaPaises!![position].recuperados}"
        Picasso.get().load("https://www.countryflags.io/${listaPaises!![position].codigoPais}/shiny/64.png").into(holder.bandera)
        Picasso.get().load("https://www.countryflags.io/${listaPaises!![position].codigoPais}/shiny/64.png").into(holder.banderatwo)
    }

    override fun getItemCount(): Int {
        return listaPaises!!.count()
    }



    class ViewHolder(vista:View):RecyclerView.ViewHolder(vista){
        var nombrePais:TextView?=null
        var numConfirmados:TextView?=null
        var numMuertos:TextView?=null
        var numRecuperados:TextView?=null
        var bandera:ImageView?=null
        var banderatwo:ImageView?=null

        init {
            nombrePais = vista.tvNombrePais
            numConfirmados = vista.tvConfirmados
            numMuertos = vista.tvMuertos
            numRecuperados = vista.tvRecuperados
            bandera = vista.imgPais
            banderatwo = vista.imgPais2
        }
    }



}