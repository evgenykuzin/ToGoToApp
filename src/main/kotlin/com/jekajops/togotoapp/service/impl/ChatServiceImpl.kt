package com.jekajops.togotoapp.service.impl

import com.jekajops.togotoapp.*
import com.jekajops.togotoapp.service.ChatService

class ChatServiceImpl(
    private val chatRepository: ChatRepository,
    private val messageRepository: MessageRepository
): ChatService {
    override fun createChat(chat: Chat): Chat {
        return chatRepository.save(chat.toEntity()).toDto()
    }

    override fun getChat(chatId: Long): Chat? {
        return chatRepository.get(chatId)?.toDto()
    }

    override fun deleteChat(chatId: Long) {
        chatRepository.delete(chatId)
    }

    override fun addMessage(message: Message): Message {
        //val chat = message.chatId?.let { getChat(it) } ?: throw EntityNotFoundException("Chat not found")
        return messageRepository.save(message.toEntity()).toDto()
    }
}