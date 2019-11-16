package com.example.clickerapp.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.clickerapp.R
import com.example.clickerapp.model.Gif
import com.example.clickerapp.viewmodel.CountViewModel
import com.example.clickerapp.viewmodel.GifViewModel
import java.util.*

class MainActivity : AppCompatActivity() {
    //initializing variable countviewmodel
    private lateinit var countViewModel: CountViewModel
    private lateinit var gifViewModel: GifViewModel

    //initializing the variable, counter, to 0 because # of clicks start at 0
    private var counter: Long = 0
    //function getUsername is set to get a user-inputted name in the clicker app & saves it
    private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)

    //initializing variable imgview and imagebutton to null
    private var imgview: ImageView? = null
    private var imagebutton: Button? = null

    //setting variable currentimage to an int of 0 because the current image on the screen
    //  will be the first image
    private var currentimage: Int = 0

    //setting array to switch between these 5 pictures in the drawables folder
    private var images = intArrayOf(
        R.drawable.cooky,
        R.drawable.milkteaboba,
        R.drawable.honeypeachboba,
        R.drawable.matchagreenteaboba,
        R.drawable.strawberryguavaboba
    )

    //initializes activity to run the functions inside
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)
        countViewModel.getUserCount(getUsername()).observe(this,
            androidx.lifecycle.Observer { updateCounter(it) })

        gifViewModel = ViewModelProviders.of(this).get(GifViewModel::class.java)
        gifViewModel.getRandomGif("android").observe(this,
            androidx.lifecycle.Observer { loadGif(it) })

        //function that allows the button that says "Click Me!" in emulator to set imgview to the
        //  currentimage and rotate through the pictures in the array order while clicker counts go up
        myButton.setOnClickListener {

            //imgview is finding the image when rotating images in the array of images listed above
            // it will cycle through the drawables
            imgview = findViewById<View>(R.id.myImage) as ImageView
    //        imagebutton = findViewById<View>(R.id.buttonNext) as Button

    //       imagebutton!!.setOnClickListener {
     //           object : View.OnClickListener {
     //               override fun onClick(v: View) {

                        //goes to the next picture when button is clicked
                        currentimage++
                        currentimage %= images.size
                        //when button is clicked, it will show the next picture because
                        //  it sets imgview to the current image
                        imgview!!.setImageResource(images[currentimage])
     //               }
    //            }
     //       }

            //in ViewModel, this pairs the username to the number of counts and saves
            //  username in emulator so they can resume where they ended in last session
            countViewModel.setUserCount(getUsername(), counter + 1)
        }
    }

    //function to update the counter that counts how many clicks in the app
    //  and will save the # of clicks to the username entered in the beginning
    // **uncommenting the myImage.rotate90() will allow images to rotate 90 degrees clockwise
    private fun updateCounter(count: Long) {
        counter = count
        //myImage.rotate90()
        Drip1.text = counter.toString()
    }

    private fun loadGif(gif: Gif){
        Glide.with(this)
            .load(gif.url)
            .into(image)
    }
}