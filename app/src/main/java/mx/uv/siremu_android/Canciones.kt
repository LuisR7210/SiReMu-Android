package mx.uv.siremu_android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Canciones : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canciones)
        val miLista = findViewById<ListView>(R.id.lista_principal2)
        miLista.adapter = miAdaptador(this)
        this.title = intent.getStringExtra("Nombre_Album")
    }

    private class miAdaptador(contexto: Context) : BaseAdapter() {

        private val miContexto: Context

        private val listaCanciones = arrayListOf<String>(
            "Giant", "Radioactive", "Know Your Worth"
        )

        private val listaAutores = arrayListOf<String>(
            "Calvin Harris", "Imagine Dragons", "Khalid"
        )

        init {
            miContexto = contexto
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(miContexto)
            val rowMain = layoutInflater.inflate(R.layout.fila_lista_canciones, parent, false)
            val nombreCancion = rowMain.findViewById<TextView>(R.id.nombre_cancion)
            nombreCancion.text = listaCanciones[position]
            val nombreArtista = rowMain.findViewById<TextView>(R.id.nombre_artista)
            nombreArtista.text = listaAutores[position]
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
            return 3
        }

    }
}
