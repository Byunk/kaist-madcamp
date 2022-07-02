package com.example.cs496_pj1.contacts

import android.provider.ContactsContract
import com.example.cs496_pj1.models.Contacts
/*
class FromDeviceFetcher {

    private fun fetchContacts(): ArrayList<Contacts> {
        var contactsList: ArrayList<Contacts> = arrayListOf()

        // Fetching Contacts
        val resolver = getContentResolver()
        val cursor = resolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC")
        while (cursor!!.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

            val contactModel = Contacts(name, phoneNumber)
            contactsList.add(contactModel)
            //Log.d("name>>", name + "  " + phoneNumber)
        }
        cursor.close()

        return contactsList
    }

}*/