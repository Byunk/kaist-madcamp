package com.example.cs496_pj1.contacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import com.example.cs496_pj1.R

class UserContactEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_contact_edit)

        val userName = findViewById<EditText>(R.id.et_userName)
        val userNumber = findViewById<EditText>(R.id.et_userNumber)
        val btn_save = findViewById<ImageButton>(R.id.save_button)

        userName.setText(intent.getStringExtra("name"))
        userNumber.setText(intent.getStringExtra("number"))

        //TODO: Editing really Edit json file

        btn_save.setOnClickListener(View.OnClickListener() {
            /*
            val intent = Intent().apply {
                putExtra("name", userName.text)
                putExtra("number", userNumber.text)
            }*/

            // Add to Json Data
            //setResult(RESULT_OK, intent)
            finish()
        })
    }
}