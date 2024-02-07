package edu.uw.ischool.jsaleh1.tipcalc

import android.icu.text.DecimalFormat
import android.icu.text.NumberFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var current: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val serviceAmt = findViewById<EditText>(R.id.editAmt)
        val button = findViewById<Button>(R.id.button)

        serviceAmt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                button.isEnabled = current != ""
                if (s.toString() != current) {
                    val cleanString: String = s.replace("""[$,.]""".toRegex(), "")

                    val parsed = cleanString.toDouble()
                    var formatted = ""
                    if(parsed > 0.00) {
                        formatted = NumberFormat.getCurrencyInstance().format(parsed / 100)
                    }

                    current = formatted
                    serviceAmt.setText(formatted)
                    serviceAmt.setSelection(formatted.length)
                }
            }
        })

        button.setOnClickListener {
            val amount = current.replace("$", "").toDouble()
            val tipAmount = amount * 0.15
            val formattedTip = NumberFormat.getCurrencyInstance().format(tipAmount)
            val toastMessage = "Tip Amount: $formattedTip"
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}