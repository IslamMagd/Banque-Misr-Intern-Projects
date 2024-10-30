package com.example.contactsapp.data

import com.example.contactsapp.R
import com.example.contactsapp.model.Contact

class DataSource {
    fun getContactsData(): List<Contact> {
        val contacts = mutableListOf<Contact>()
        contacts.add(
            Contact(
                R.drawable.auntie,
                R.string.auntie,
                "+201012345678"
            )
        )
        contacts.add(
            Contact(
                R.drawable.brother,
                R.string.brother,
                "+201234567890"
            )
        )
        contacts.add(
            Contact(
                R.drawable.daughter,
                R.string.daughter,
                "+201234567890"
            )
        )
        contacts.add(
            Contact(
                R.drawable.friend_1,
                R.string.friend1,
                "+201345678901"
            )
        )
        contacts.add(
            Contact(R.drawable.friend_2,
                R.string.friend2,
                "+201456789012"
            )
        )
        contacts.add(
            Contact(
                R.drawable.grandfather,
                R.string.grandfather,
                "+201567890123"
            )
        )
        contacts.add(
            Contact(
                R.drawable.granny,
                R.string.granny,
                "+201678901234"
            )
        )
        contacts.add(
            Contact(
                R.drawable.neigbour,
                R.string.neighbour,
                "+201789012345"
            )
        )
        contacts.add(
            Contact(
                R.drawable.sister,
                R.string.sister,
                "+201890123456"
            )
        )
        contacts.add(
            Contact(
                R.drawable.son,
                R.string.son,
                "+201901234567"
            )
        )
        contacts.add(
            Contact(
                R.drawable.uncle,
                R.string.uncle,
                "+201123456789"
            )
        )
        return contacts
    }
}