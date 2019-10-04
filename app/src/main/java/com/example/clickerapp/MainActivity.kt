package com.example.clickerapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clickerapp.util.rotate90
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private var counter: Long = 0
    private var username: String = ""

    private var imgview: ImageView? = null
    private var imagebutton: Button? = null
    fun getStore() = getPreferences(Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonclick();

        username = intent.extras?.get("username").toString()

        if (savedInstanceState != null) {
            updateCounter(savedInstanceState.getLong(username, 0))
        }
        else if(getStore().contains(username)){
            updateCounter(getStore().getLong(username, 0))
        }

        myButton.setOnClickListener{
            counter++
            myImage.rotate90()
            Drip1.text = counter.toString()

            myButton.text = when (counter) {
                1L -> "more clicking"
                in 2.. 9 -> myButton.text.toString().plus("!")
                else -> myButton.text
            }
        }
    }

    private fun updateCounter(count: Long) {
          counter = count
          Drip1.text = counter.toString()
    }

    override fun onPause() {
        super.onPause()
        getStore().edit().putLong(username, counter).apply()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putLong(username, counter)
        }
        super.onSaveInstanceState(outState)
    }


    private var currentimage: Int = 0
    internal var images = intArrayOf(R.drawable.cooky, R.drawable.milkteaboba)

    fun buttonclick() {
        imgview = findViewById<View>(R.id.myImage) as ImageView
        imagebutton = findViewById<View>(R.id.buttonNext) as Button
        imagebutton!!.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View) {
                    currentimage++
                    currentimage = currentimage % images.size
                    imgview!!.setImageResource(images[currentimage])
                }
            })
    }
 //   companion object {
 //       private const val COUNTER_KEY = "counter key"
 //   }
}
