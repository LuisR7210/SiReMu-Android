package mx.uv.siremu_android

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_cancion.*
import org.json.JSONObject


class Cancion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cancion)

        tvLyrics.movementMethod = ScrollingMovementMethod();

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.lyrics.ovh/v1/Coldplay/Adventure of a Lifetime"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                tvLyrics.text = response.getString("lyrics")
            },
            Response.ErrorListener { error ->
                // TODO: Handle error
            }
        )


        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }
}
