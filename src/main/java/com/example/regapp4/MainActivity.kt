package com.example.regapp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity()
{


        private lateinit var auth: FirebaseAuth

        private lateinit var emailEt: EditText
        private lateinit var passwordEt: EditText

        private lateinit var signupBtn: Button
        private lateinit var loginBtn: Button

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            emailEt = findViewById(R.id.email_edt_text)
            passwordEt = findViewById(R.id.pass_edt_text)

            signupBtn = findViewById(R.id.signup_btn)
            loginBtn = findViewById(R.id.login_btn)
            auth = FirebaseAuth.getInstance()

            loginBtn.setOnClickListener {
                var email: String = emailEt.text.toString()
                var password: String = passwordEt.text.toString()

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(this@MainActivity, "Please fill all the fields", Toast.LENGTH_LONG).show()
                } else{
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
                        if(task.isSuccessful) {
                            Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_LONG).show()
                            val intent = Intent(this, LoggedIn::class.java)
                            startActivity(intent)
                            finish()
                        }else {
                            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show()
                        }
                    })
                }
            }

            signupBtn.setOnClickListener{
                val intent = Intent(this, Signup::class.java)
                startActivity(intent)
                finish()
            }


        }
}