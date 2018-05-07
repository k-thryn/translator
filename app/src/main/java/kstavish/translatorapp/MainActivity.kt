package kstavish.translatorapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.translate_text_input
import kstavish.translatorapp.R.id.translate_text_input
import org.json.JSONObject
import java.net.URL
import java.util.concurrent.Executors

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
        val url = "$base$endpoint?text=$text"

        var translated: String? = "your shit's not getting updated"

        // Make call
        val request = JsonObjectRequest(Request.Method.POST, url, null,
                Response.Listener { response ->
                    translated = response.getJSONObject("contents")?.getString("translation")
                },
                Response.ErrorListener { error ->
                    System.out.println("Something's gone wrong: " + error.toString())
                })
        System.out.println(request.body)
        val toast = Toast.makeText(this, translated, Toast.LENGTH_SHORT)
        toast.show()
    }
}
