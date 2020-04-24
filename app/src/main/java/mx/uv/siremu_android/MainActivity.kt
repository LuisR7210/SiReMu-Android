package mx.uv.siremu_android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val miLista = findViewById<ListView>(R.id.lista_principal)
        miLista.adapter = miAdaptador(this)

    }

    private class miAdaptador(contexto: Context) : BaseAdapter() {

        private val miContexto: Context

        init {
            miContexto = contexto
        }

        //genera cada una de las filas
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val texto = TextView(miContexto)
            texto.text = "Hola"
            return texto
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
            return 10
        }

    }
}