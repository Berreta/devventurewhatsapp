package com.example.meuzapdrawer.ui.Contatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.meuzapdrawer.R
import com.example.meuzapdrawer.model.Contact
import com.example.meuzapdrawer.model.User

class ContactsAdapter(): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
    var contact: ArrayList<Contact> = ArrayList<Contact>()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val ContactName: TextView = itemView.findViewById(R.id.contact_name)
        private val ContactDetail: TextView = itemView.findViewById(R.id.contact_detail)
        private val ContactImage: ImageView = itemView.findViewById(R.id.contact_image)

        fun setUser(Contact: Contact){
            ContactName.text = Contact.name
            ContactDetail.text = Contact.email
            //TODO: set image
        }

    }
    fun setContactsList(Contacts: ArrayList<Contact>){
        this.contact = Contacts
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.contacts_item, parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.setUser(contact[position])
    }

    override fun getItemCount(): Int {
        return contact.size
    }


}