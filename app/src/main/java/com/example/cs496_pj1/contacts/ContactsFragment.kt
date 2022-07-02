package com.example.cs496_pj1.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cs496_pj1.databinding.FragmentContactsBinding
import com.example.cs496_pj1.models.Contacts
import androidx.recyclerview.widget.LinearLayoutManager
import org.json.JSONObject
import java.io.BufferedReader
import java.io.FileReader

class ContactsFragment : Fragment() {
    private lateinit var binding: FragmentContactsBinding
    private var contactsList: ArrayList<Contacts> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        // Read Contacts from Device
        contactsList.clear()

        val assets = resources.assets
        val inputStream = assets.open("contacts.json")
        val jsonString = inputStream.bufferedReader().use{ it.readText() }
        // Parsing
        val jObject = JSONObject(jsonString)
        val jArray = jObject.getJSONArray("contacts")

        val userFile = requireContext().getFileStreamPath("user.txt")
        if(userFile.exists()) {
            val fileReader = FileReader(userFile)
            val bufferedReader = BufferedReader(fileReader)
            val temp = arrayListOf<String>()
            bufferedReader.readLines().forEach {
                temp.add(it)
            }
            if(temp.size < 2) {
                contactsList.add(Contacts("변경호", "010-1234-5678"))
            }
            else {
                contactsList.add(Contacts(temp[0], temp[1]))
            }
        } else {
            contactsList.add(Contacts("김예은", "010-8765-4321"))
        }

        for(i in 0 until jArray.length()) {
            val obj = jArray.getJSONObject(i)
            val name = obj.getString("name")
            val number = obj.getString("number")
            contactsList.add(Contacts(name, number))
        }

        binding.rvContacts.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvContacts.setHasFixedSize(true)
        binding.rvContacts.adapter = ContactsAdapter(contactsList)
        //ContactsAdapter(contactsList).notifyDataSetChanged()
    }
}