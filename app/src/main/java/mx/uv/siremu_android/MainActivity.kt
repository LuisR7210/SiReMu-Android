package mx.uv.siremu_android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import mx.uv.siremu_android.data.model.ListaAlbumes

class MainActivity : AppCompatActivity() {
    private lateinit var listaAlb : ListaAlbumes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        crearLista()

    }

    private fun crearLista(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://theaudiodb.com/api/v1/json/1/searchalbum.php?s=coldplay&s=coldplay"
        val mapHeaders : MutableMap<String, String> = mutableMapOf<String,String>() //agregar headers necesarios
        val myGsonRequest: GsonRequest<ListaAlbumes> = GsonRequest<ListaAlbumes>(
            url,
            ListaAlbumes::class.java,
            mapHeaders,
            myRequestSuccessListener(), //Success Listener
            myRequestErrorListener() //Error Listener
        )
        MySingleton.getInstance(this).addToRequestQueue(myGsonRequest)
    }

    private fun myRequestSuccessListener(): Response.Listener<ListaAlbumes> {
        return Response.Listener<ListaAlbumes> { response ->
            listaAlb = response
            tvProbar.text = response.album?.get(1)?.strAlbum ?: toString()
            listas_reproduccion.adapter = MiAdaptador(this, listaAlb)
            listas_reproduccion.setOnItemClickListener{parent,view,position,id->
                val intent = Intent(this,lista_repr::class.java).apply {
                    putExtra("nombre","lista[position].toString()")
                }
                this.startActivity(intent)
            }
        }
    }

    private fun myRequestErrorListener(): Response.ErrorListener {
        return Response.ErrorListener { error ->
            tvProbar.text = "Fallo"
        }
    }

    private class MiAdaptador(contexto: Context, listaAlb: ListaAlbumes) : BaseAdapter() {

        private val miContexto: Context = contexto

        private val listaA: ListaAlbumes = listaAlb

        private val lista = arrayListOf<String>(
            "Biblioteca local","Canciones que me gustan","Musica descargada","Mis canciones","Mejores Aerosmith","Linkin Parkkkk","Mi rock " +
                    "en español", "Musica para dormir"
        )

        //genera cada una de las filas
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(miContexto)
            val rowMain = layoutInflater.inflate(R.layout.fila_lista_main, parent, false)
            val nombreAlbum = rowMain.findViewById<TextView>(R.id.tvAlbum)
            nombreAlbum.text = listaA.album?.get(position)?.strAlbum ?: toString()
            val nombreArtista = rowMain.findViewById<TextView>(R.id.tvArtista)
            nombreArtista.text = listaA.album?.get(position)?.strArtist ?: toString()
            val imagenAlbum  = rowMain.findViewById<ImageView>(R.id.imgAlbum)
            GlideApp.with(miContexto).load(listaA.album?.get(position)?.strAlbumThumb).into(imagenAlbum);
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
            return lista.size
        }

    }
}