package com.example.clickerapp.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.clickerapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //object TextWatcher allows the addTextChangedListener method to save any changes in the loginUsernameField
        loginUsernameField.addTextChangedListener(object : TextWatcher {
            //the 3 functions below are called to state any changes made to the text
            // when called, function beforeTextChanged will save the old text in CharSequence
            //  and keep the old text until user wants to change it
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //unused
            }
            //when called, function onTextChanged will hold any changed text in CharSequence and have
            //  the number of characters in p3 beginning in p1 replace the length of the old text in p2
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //unused

            // when called, function afterTextChanged allows username field to be editable, meaning
            //   it will take any username
            }
            override fun afterTextChanged(p0: Editable?) {
                //unused
            }
        })

        //login button is set to start saving list of usernames in the activity and loginUsernameField
        //  allows user to enter any name to save to activity
        loginButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).apply { putExtra ( "username", loginUsernameField.text)
            })
        }
    }}
