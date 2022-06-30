package com.example.cs496_pj1.contacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.cs496_pj1.R
import org.json.JSONObject
import java.io.File

class UserContactEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_contact_edit)

        var userName = findViewById<EditText>(R.id.et_userName)
        var userNumber = findViewById<EditText>(R.id.et_userNumber)
        var btn_save = findViewById<ImageButton>(R.id.save_button)
        //var btn_delete = findViewById<Button>(R.id.delete_button)

        userName.setText(intent.getStringExtra("name"))
        userNumber.setText(intent.getStringExtra("number"))

        //TODO: Editing really Edit json file

        btn_save.setOnClickListener(View.OnClickListener() {
            val intent = Intent().apply {
                putExtra("name", userName.text)
                putExtra("number", userNumber.text)
            }

            // Add to Json Data
            setResult(RESULT_OK, intent)
            finish()
        })
    /*
        btn_delete.setOnClickListener(View.OnClickListener() {
            val intent = Intent()
            // Delete

            setResult(RESULT_OK, intent)
            finish()
        })*/
    }
}