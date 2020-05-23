package mx.uv.siremu_android

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cancion.*
import mx.uv.siremu_android.data.model.ListaAlbumes
import org.json.JSONObject


class Cancion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancion)

        tvLyrics.movementMethod = ScrollingMovementMethod();

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.lyrics.ovh/v1/Coldplay/Adventure of a Lifetime"

        /*val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                tvLyrics.text = response.getString("lyrics")
            },
            Response.ErrorListener { error ->
                tvLyrics.text = "No se encontraron los lyrics para esta canción"
            }
        )*/
        val mapHeaders : MutableMap<String, String> = mutableMapOf<String,String>() //agregar headers necesarios
        val myGsonRequest: GsonRequest<ListaAlbumes> = GsonRequest<ListaAlbumes>(
            url,
            ListaAlbumes::class.java,
            mapHeaders,
            myRequestSuccessListener(), //Success Listener
            myRequestErrorListener() //Error Listener
        )

        MySingleton.getInstance(this).addToRequestQueue(myGsonRequest)

        // Add the request to the RequestQueue.
        //queue.add(jsonObjectRequest)

    }


    private fun myRequestSuccessListener(): Response.Listener<ListaAlbumes> {
        return Response.Listener<ListaAlbumes> {response ->
            textView2.text = "Probando"
            tvLyrics.text = response.album?.get(1)?.strAlbum ?:toString()
        }
    }

    private fun myRequestErrorListener(): Response.ErrorListener {
        return Response.ErrorListener {error ->
            Log.e("ERROR", error.toString())
            //Código en caso de error
        }
    }

}


