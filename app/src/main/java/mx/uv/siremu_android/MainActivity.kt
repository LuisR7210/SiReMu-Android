package mx.uv.siremu_android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listasReproduccion = findViewById<ListView>(R.id.listas_reproduccion)
        listasReproduccion.adapter = miAdaptador(this)

        listasReproduccion.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Canciones::class.java)
            val textView = view.findViewById(R.id.nombre_comidas) as TextView
            intent.putExtra("Nombre_Album", textView.text.toString())
            startActivity(intent)
        }

    }

    fun cancionesClick(v: View) {

    }

    private class miAdaptador(contexto: Context) : BaseAdapter() {

        private val miContexto: Context

        private val listaComidas = arrayListOf<String>(
            "Biblioteca local","Canciones que me gustan","Musica descargada","Mis canciones","Mejores Aerosmith","Linkin Parkkkk","Mi rock " +
                    "en español", "Musica para dormir"
        )

        init {
            miContexto = contexto
        }

        //genera cada una de las filas
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(miContexto)
            val rowMain = layoutInflater.inflate(R.layout.fila_lista_main, parent, false)
            val nombreComida = rowMain.findViewById<TextView>(R.id.nombre_comidas)
            nombreComida.text = listaComidas[position]
            return rowMain
        }

        override fun getItem(position: Int): Any {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        //regresa el id de cada elemento de la lista
        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        // cuántas filas genera
        override fun getCount(): Int {
            return listaComidas.size
        }

    }
}