package com.example.rastreadorcovid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    var adaptadorPaises:PaisAdapter?=null
    var listaPaises:ArrayList<Pais>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listaPaises = ArrayList<Pais>()

        adaptadorPaises = PaisAdapter(listaPaises!!, this)
        myRecyclerCovid.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        myRecyclerCovid.adapter = adaptadorPaises


        val queue = Volley.newRequestQueue(this)
        val url = "https://wuhan-coronavirus-api.laeyoung.endpoint.ainize.ai/jhu-edu/latest"



        val peticionDatosCovid = JsonArrayRequest(Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                for (index in 0..response.length() - 1) {
                    try {
                        val paisJson = response.getJSONObject(index)
                        val nombrePais = paisJson.getString("countryregion")
                        val numConfirmados = paisJson.getInt("confirmed")
                        val nuMuertos = paisJson.getInt("deaths")
                        val numRecuperados = paisJson.getInt("recovered")
                        val countryCodeJson = paisJson.getJSONObject("countrycode")
                        val codigoPais = countryCodeJson.getString("iso2")

                        val paiIndividual = Pais(
                            nombrePais,
                            numConfirmados,
                            nuMuertos,
                            numRecuperados,
                            codigoPais
                        )

                        listaPaises!!.add(paiIndividual)
                    } catch (e: JSONException) {
                        Log.wtf("jsonerror", e.localizedMessage)
                    }

                }
                listaPaises!!.sortByDescending { it.confirmados }
                adaptadorPaises!!.notifyDataSetChanged()
            },
            Response.ErrorListener { error ->
                Log.e("Error", error.localizedMessage)
            })

        queue.add(peticionDatosCovid)

    }
}