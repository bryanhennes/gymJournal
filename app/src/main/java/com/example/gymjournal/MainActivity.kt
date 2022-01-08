package com.example.gymjournal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var emailButton: ImageView
    lateinit var passwordButton: Button
    lateinit var loginTV: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        loginTV = findViewById(R.id.loginTV)
        emailEditText = findViewById(R.id.emailET)
        passwordEditText = findViewById(R.id.passwordET)
        emailButton = findViewById(R.id.emailButton)
        passwordButton = findViewById(R.id.checkPasswordButton)



        emailEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            //on text changed check to see when email is of valid format
            //if so, show login button icon
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                displayLoginButton()
                if(checkValidEmailFormat(s)) {
                    emailButton.setColorFilter(resources.getColor(R.color.valid))
                } else {
                    emailButton.setColorFilter(resources.getColor(R.color.invalid))
                }
            }
        })

        emailButton.setOnClickListener {
            if (emailExists() && validPassword()) {
                emailEditText.visibility = View.GONE
                emailButton.visibility = View.GONE
                loginTV.visibility = View.INVISIBLE
                passwordEditText.visibility = View.VISIBLE
                passwordButton.visibility = View.VISIBLE
            }
        }

        //go to home if login successful
        passwordButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, Home::class.java))
        }


    }

    //check for valid email
    fun checkValidEmailFormat(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    //show email button
    fun displayLoginButton() {
        emailButton.visibility = View.VISIBLE
    }

    //hide email button
    fun hideLoginButton() {
        emailButton.visibility = View.GONE
    }

    //if email exists in database
    fun emailExists(): Boolean {
        return true
    }

    //if correct credentials are entered
    fun validPassword(): Boolean {
        return true
    }
}

