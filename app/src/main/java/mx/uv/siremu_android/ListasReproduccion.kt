package mx.uv.siremu_android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class ListasReproduccion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.consultar_mis_lr)
        val miLista = findViewById<ListView>(R.id.lista_lr)
        miLista.adapter = miAdaptador(this)

        miLista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Canciones::class.java)
            startActivity(intent)
        }
    }
    private class miAdaptador(contexto: Context) : BaseAdapter() {

        private val miContexto: Context

        private val lista = arrayListOf<String>(
            "Biblioteca local","Canciones que me gustan","Musica descargada","Mis canciones","Mejores Aerosmith","Linkin Parkkkk","Mi rock " +
                    "en español", "Musica para dormir"
        )

        init {
            miContexto = contexto
        }

        //genera cada una de las filas
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            /*val texto = TextView(miContexto)
            texto.text = "Hola"
            return texto*/
            val layoutInflater = LayoutInflater.from(miContexto)
            val townmain = layoutInflater.inflate(R.layout.lista_reproduccion_cmldr,parent, false)
            val fila = townmain.findViewById<TextView>(R.id.nombre)
            fila.text=lista[position]
            /*val texto = TextView(miContexto)
            texto.text = "Soy Luis Angel"
            return texto*/
            return townmain
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
//            return 10
            return lista.size
        }

    }
}