package com.jackpan.mydemoproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.button_1 ->{
                val intent = Intent()
                intent.setClass(this@MainActivity,MapsActivity::class.java)
                startActivity(intent)
            }
        }
    }

    lateinit var mMapButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLayout()
    }

    fun initLayout(){
        mMapButton = findViewById(R.id.button_1)
        mMapButton.setOnClickListener(this)
    }
}
