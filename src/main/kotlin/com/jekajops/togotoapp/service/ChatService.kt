package com.jekajops.togotoapp.service

import com.jekajops.togotoapp.Chat
import com.jekajops.togotoapp.Message
import javax.persistence.EntityNotFoundException

interface ChatService {
    fun createChat(chat: Chat): Chat
    fun getChat(chatId: Long): Chat?
    fun deleteChat(chatId: Long)
    fun addMessage(message: Message): Message
}