package com.example.expandablebottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var bottomSheet: BottomSheet
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.openDialog)
        button.setOnClickListener{openDialog()}
    }

    private fun openDialog() {
        bottomSheet=BottomSheet()
        bottomSheet.show(supportFragmentManager,"TAG")

    }
}