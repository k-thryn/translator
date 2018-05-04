package kstavish.translatorapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.translate_text_input

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Translate to yoda
    fun translate(view: View) {
        // Get text to be translated
        val inputText = translate_text_input.text.toString();
        // TODO: translate
        val myToast = Toast.makeText(this, "Translation complete", Toast.LENGTH_SHORT)
        myToast.show()
    }
}
