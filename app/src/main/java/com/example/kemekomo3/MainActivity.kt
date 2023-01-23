package com.example.kemekomo3


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kemekomo3.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonCalcular.setOnClickListener { calculoPropina() }
    }
    

    @SuppressLint("Formato de Texto Inválido")
    private fun calculoPropina() {
        val valorTexto = binding.costeServicio.text.toString()
        val coste = valorTexto.toDoubleOrNull()
        if (coste == null) {
            binding.resultadoPropina.text = ""
            return
        }
        
        

        val porcentajePropinas = when (binding.opcionesPropina.checkedRadioButtonId) {
            R.id.opcion_veinte_porciento -> 0.20
            R.id.opcion_dieciocho_porciento -> 0.18
            else -> 0.15
        }


        var propina = porcentajePropinas * coste
        if (binding.redondeo.isChecked) {
            propina = kotlin.math.ceil(propina)
        }


        val formatoPropinas = NumberFormat.getCurrencyInstance().format(propina)
        binding.resultadoPropina.text = getString(R.string.cantPropina, formatoPropinas)
    }
}

