package com.example.provamobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

    private lateinit var editTextAmount: EditText
    private lateinit var editTextTip: EditText
    private lateinit var editTextTotal: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var porcentagem: TextView
    private var valorAmount : String= "0.0"
    private var valorTip = 0.0
    private var total = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBarTip = findViewById(R.id.seekBar)
        editTextTip = findViewById(R.id.editTextTip)
        editTextAmount = findViewById(R.id.editTextAmout)
        editTextTotal = findViewById(R.id.editTextTotal)
        porcentagem = findViewById(R.id.textViewPorcentagem)


        editTextAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
               if (!editTextAmount.text.isEmpty()){ //Se o campo nao estiver vazio entra nesse if, se tiver vazio não faz nada
                   var valorAmout: Double = editTextAmount.text.toString().toDouble()
                   var valorTip: Double = (seekBarTip.progress * valorAmout) / 100
                   porcentagem.text = "${seekBarTip.progress}%"

                   editTextTip.setText("$:$valorTip")
                   total = valorAmout + valorTip
                   editTextTotal.setText("$:$total")
               }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if (editTextAmount.text.isEmpty()) {
                    seekBarTip.isEnabled = false
                    valorAmount = editTextAmount.text.toString()
                    valorTip = (15 * valorAmount.toDouble()) / 100
                    porcentagem.text = "$15%"

                    editTextTip.setText("$:$valorTip")
                    var total = valorAmount + valorTip
                    editTextTotal.setText("$:$total")
                } else {
                    seekBarTip.isEnabled = true
                    var valorAmout: Double = editTextAmount.text.toString().toDouble()
                    var valorTip: Double = (progress * valorAmout) / 100
                    porcentagem.text = "$progress%"

                    editTextTip.setText("$:$valorTip")
                    total = valorAmout + valorTip
                    editTextTotal.setText("$:$total")
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

    }
}
