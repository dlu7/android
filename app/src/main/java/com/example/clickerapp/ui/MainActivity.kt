package com.example.clickerapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clickerapp.util.rotate90
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.example.clickerapp.R
import com.example.clickerapp.viewmodel.CountViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var countViewModel: CountViewModel

    private var counter: Long = 0
    private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)

    private var imgview: ImageView? = null
    private var imagebutton: Button? = null

    private var currentimage: Int = 0
    private var images = intArrayOf(
        R.drawable.cooky,
        R.drawable.milkteaboba,
        R.drawable.honeypeachboba,
        R.drawable.matchagreenteaboba,
        R.drawable.strawberryguavaboba
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)
        countViewModel.getUserCount(getUsername()).observe(this,
            androidx.lifecycle.Observer { updateCounter(it) })

        myButton.setOnClickListener {

            imgview = findViewById<View>(R.id.myImage) as ImageView
    //        imagebutton = findViewById<View>(R.id.buttonNext) as Button

    //       imagebutton!!.setOnClickListener {
     //           object : View.OnClickListener {
     //               override fun onClick(v: View) {
                        currentimage++
                        currentimage %= images.size
                        imgview!!.setImageResource(images[currentimage])
     //               }
    //            }
     //       }

            countViewModel.setUserCount(getUsername(), counter + 1)
        }


    }

    private fun updateCounter(count: Long) {
        counter = count
        //myImage.rotate90()
        Drip1.text = counter.toString()
    }
}