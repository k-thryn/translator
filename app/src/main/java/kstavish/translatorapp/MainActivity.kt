package kstavish.translatorapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.translate_text_input
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Onclick function; translate to Yoda
    fun translateYoda(view: View) {
        val endpoint = "/yoda.json"
        translate(view, endpoint)
    }

    // Onclick function; translate to Pirate
    fun translatePirate(view: View) {
        val endpoint = "/pirate.json"
        translate(view, endpoint)
    }

    // Onclick function: translate to Pig Latin
    fun translatePigLatin(view: View) {
        val endpoint = "/piglatin.json"
        translate(view, endpoint)
    }

    // Translate
    private fun translate(view: View, endpoint: String) {
        // Generate full url using root url and text
        val base = "http://api.funtranslations.com/translate"
        val text = translate_text_input.text.toString()
        val url = "$base$endpoint"

        var params = JSONObject()
        params.put("text", text)

        // Make call
        val queue = Volley.newRequestQueue(this)
        val request = object : JsonObjectRequest(Request.Method.POST, url, params,
                Response.Listener<JSONObject> { response ->
                    var translated = response.getJSONObject("contents")?.getString("translated")
                    val toast = Toast.makeText(this, translated, Toast.LENGTH_SHORT)
                    toast.show()
                },
                Response.ErrorListener { error ->
                    val toast = Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT)
                    toast.show()
                }) {
            override fun getHeaders(): Map<String, String> {
                return HashMap()
            }
        }
        queue.add(request)
    }
}
