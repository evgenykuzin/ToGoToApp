package com.jekajops.togotoapp

import java.time.LocalDateTime

data class Event(
    val id: Long?,
    val name: String?,
    val title: String?,
    val geoTag: String?,
    val participants: List<User>,
    val viewers: List<User>,
    val publications: Set<Publication>,
    val createdAt: LocalDateTime?
)

data class Publication(
    val id: Long?,
    val eventId: Long?,
    val publisherId: Long?,
    val publicationDateTime: LocalDateTime?,
    val hashtags: Set<String>,
    val mediaData: MediaData,
    val reactions: List<Reaction>,
    val comments: List<Comment>
)

sealed class MediaData {
    data class Photo(val url: String) : MediaData()
    data class Video(val url: String, val durationSeconds: Int) : MediaData()
}

data class Reaction(
    val id: Long?,
    val publisherId: Long?,
    val publicationId: Long?,
    val emojiCode: String?,
    val reactionDateTime: LocalDateTime?
)

data class Comment(
    val id: Long?,
    val publisherId: Long?,
    val publicationId: Long?,
    val text: String?,
    val commentDateTime: LocalDateTime?,
    val replies: List<Comment>
)

data class User(
    val id: Long?,
    val name: String?,
    val bio: String?,
    val geoTag: String?,
    val rating: Double?,
    val friends: List<Friend>,
    val subscribers: List<User>,
    val events: List<Event>
)

data class Friend(
    val id: Long?,
    val userId: Long?,
    val friendId: Long?
)

data class Chat(
    val id: Long?,
    val participants: List<User>,
    val messages: List<Message>
)

data class Message(
    val id: Long?,
    val senderId: Long?,
    val text: String?,
    val sentDateTime: LocalDateTime?,
    val chatId: Long?
)

data class Invite(
    val id: Long?,
    val fromUserId: Long?,
    val toUserId: Long?,
    val eventId: Long?
)