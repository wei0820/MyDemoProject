package com.jackpan.mydemoproject

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.jackpan.mydemoproject.Data.XmlData
import java.io.IOException
import org.jsoup.Jsoup


class XmlActivity : AppCompatActivity() {
    var mArrayList = ArrayList<XmlData>()
    var mAdapter: MyAdapter? = null
    lateinit var mProgressDialog: ProgressDialog


    lateinit var mListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml)
        getxml()
        mListView = findViewById(R.id.listview)
        mAdapter = MyAdapter(mArrayList)
        mListView.adapter =  mAdapter




    }

    fun getxml() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setTitle("讀取中")
        mProgressDialog.setMessage("請稍候")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()

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
                        mAdapter!!.notifyDataSetChanged()
                        mProgressDialog.dismiss()
                    }

                } catch (e: IOException) {

                }

            }


        }.start()


    }

    inner class MyAdapter(var mAllData: ArrayList<XmlData>?) : BaseAdapter() {
        fun updateData(datas: ArrayList<XmlData>) {
            mAllData = datas
            notifyDataSetChanged()
        }

        override fun getCount(): Int {
            return mAllData!!.size
        }

        override fun getItem(position: Int): Any {
            return mAllData!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val data = mAllData!![position]
            if (convertView == null) {
                convertView = LayoutInflater.from(this@XmlActivity).inflate(
                        R.layout.myitem, null)
            }
            var mTitle: TextView = convertView!!.findViewById(R.id.title)
            var mDescription :TextView = convertView!!.findViewById(R.id.description)
            mTitle.text = data.title
            mDescription.text = data.description
            return convertView
        }

    }

}
