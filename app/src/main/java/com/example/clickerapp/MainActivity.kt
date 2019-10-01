package com.example.clickerapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clickerapp.util.rotate90
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var counter: Long = 0
    private var username: String = ""
    fun getStore() = getPreferences(Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

 //   companion object {
 //       private const val COUNTER_KEY = "counter key"
 //   }
}
