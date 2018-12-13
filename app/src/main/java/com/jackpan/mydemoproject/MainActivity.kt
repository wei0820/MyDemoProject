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
            R.id.button_3 ->{
                val intent = Intent()
                intent.setClass(this@MainActivity,XmlActivity::class.java)
                startActivity(intent)
            }
        }
    }

    lateinit var mMapButton: Button
    lateinit var mCameraButton:Button
    lateinit var mXmlButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLayout()
    }

    fun initLayout(){
        mMapButton = findViewById(R.id.button_1)
        mCameraButton = findViewById(R.id.button_2)
        mXmlButton = findViewById(R.id.button_3)
        mMapButton.setOnClickListener(this)
        mXmlButton.setOnClickListener(this)
    }
}
