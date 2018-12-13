package com.jackpan.mydemoproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jackpan.mydemoproject.Data.XmlData
import java.io.IOException
import org.jsoup.Jsoup


class XmlActivity : AppCompatActivity() {
    var mArrayList = ArrayList<XmlData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml)
        getxml()


    }

    fun getxml() {

        Thread {
            run {
                try {

                    val doc = Jsoup.connect("http://news.ltn.com.tw/rss/focus.xml").get()
                    for (element in doc.select("item")) {
                        var mXmlData = XmlData()

                        for (element in element.select("title")) {
                            mXmlData.setTitle(element.text())

                        }
                        for (description in element.select("description")) {
                            mXmlData.description = description.text()

                        }
                        for (link in element.select("link")) {
                            mXmlData.link = link.text()

                        }
                        mArrayList.add(mXmlData)

                    }


                    runOnUiThread {
                        Log.d("jack",mArrayList.size.toString())

                        for (xmlData in mArrayList) {
                            Log.d("jack",xmlData.link)
                        }
                    }

                } catch (e: IOException) {

                }

            }


        }.start()


    }


}
