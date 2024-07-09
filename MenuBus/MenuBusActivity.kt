package com.example.busapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MenuBusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_bus)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val ivProfile = findViewById<ImageView>(R.id.ivProfile)
        val lvBus = findViewById<ListView>(R.id.lvBus)

        val busList = arrayOf("Bus 3D", "Bus 4D", "Bus 2D", "Bus 1D")
        val halteMap = mapOf(
            "Bus 3D" to arrayOf("Halte UNISBA", "Halte BEC", "Halte Alun-Alun Bandung"),
            "Bus 4D" to arrayOf("Halte ITB", "Halte Dago", "Halte Cihampelas"),
            "Bus 2D" to arrayOf("Halte Cibaduyut", "Halte Leuwi Panjang", "Halte Cimindi"),
            "Bus 1D" to arrayOf("Halte Pasteur", "Halte Cipaganti", "Halte Gasibu")
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, busList)
        lvBus.adapter = adapter

        lvBus.setOnItemClickListener { _, _, position, _ ->
            val selectedBus = busList[position]
            val halteList = halteMap[selectedBus]

            if (halteList != null) {
                val halteAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, halteList)
                val lvHalte = ListView(this).apply {
                    adapter = halteAdapter
                }
                val parent = lvBus.parent as RelativeLayout
                parent.addView(lvHalte)
                val layoutParams = lvHalte.layoutParams as RelativeLayout.LayoutParams
                layoutParams.addRule(RelativeLayout.BELOW, lvBus.id)
                layoutParams.setMargins(0, 16, 0, 0)
                lvHalte.layoutParams = layoutParams
            } else {
                Toast.makeText(this, "Tidak ada halte yang dilewati untuk $selectedBus", Toast.LENGTH_SHORT).show()
            }
        }

        ivProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
