package com.example.cs496_pj1.contacts

import android.Manifest.permission.CALL_PHONE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj1.R
import com.example.cs496_pj1.models.Contacts
import java.util.jar.Manifest

class ContactsAdapter(val contactsList: ArrayList<Contacts>) : RecyclerView.Adapter<ContactsAdapter.CustomViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactsAdapter.CustomViewHolder {

        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.contacts_row, parent, false)
        return CustomViewHolder(view)
    }


    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(contactsList[position])
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.personName)
        val number = itemView.findViewById<TextView>(R.id.personNumber)
        val btnPhoneCall = itemView.findViewById<ImageButton>(R.id.phone_call_button)

        //val btn_add = itemView.findViewById<ImageButton>(R.id.btn_add)

        fun bind(item: Contacts) {
            name.text = item.name
            number.text = item.number

            itemView.setOnClickListener {
                val intent = Intent(context, UserContactEditActivity::class.java).apply {
                    putExtra("name", item.name)
                    putExtra("number", item.number)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

            btnPhoneCall.setOnClickListener {
                // Checking Permission
                makeCall(item)
            }
        }

        private fun makeCall(item: Contacts) {
            val number = item.number
            val callIntent: Intent = Uri.parse("tel:$number").let { number ->
                Intent(Intent.ACTION_DIAL, number)
            }
            context.startActivity(callIntent)
        }
    }

}