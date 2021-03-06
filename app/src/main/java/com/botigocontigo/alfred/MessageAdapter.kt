package com.botigocontigo.alfred

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.chat_bubble_sent.view.*
import kotlinx.android.synthetic.main.chat_bubble_received.view.*

private const val VIEW_TYPE_SENT_MESSAGE = 1
private const val VIEW_TYPE_RECEIVED_MESSAGE = 2

class MessageAdapter (val context: Context) : RecyclerView.Adapter<MessageViewHolder>() {
    private val messages: ArrayList<Message> = ArrayList()


    fun addMessage(message: Message){
        messages.add(message)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages.get(position)

        return VIEW_TYPE_RECEIVED_MESSAGE //TODO FIXME Delete and use following instead
        /*
        return if(App.user == message.user) {
            VIEW_TYPE_SENT_MESSAGE
        }
        else {
            VIEW_TYPE_RECEIVED_MESSAGE
        }
        */

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return if(viewType == VIEW_TYPE_SENT_MESSAGE) {
            MyMessageViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.chat_bubble_sent, parent, false)
            )
        } else {
            OtherMessageViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.chat_bubble_received, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages.get(position)

        holder?.bind(message)
    }


    inner class MyMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.txtMyMessage

        override fun bind(message: Message) {
            messageText.text = message.message
        }
    }

    inner class OtherMessageViewHolder (view: View) : MessageViewHolder(view) {
        private var messageText: TextView = view.txtOtherMessage

        override fun bind(message: Message) {
            messageText.text = message.message
        }
    }
}

open class MessageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(message:Message) {}
}

