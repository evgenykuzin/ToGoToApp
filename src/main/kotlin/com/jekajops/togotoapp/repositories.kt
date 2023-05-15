package com.jekajops.togotoapp

import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<EventModel, Long> {
    fun create(EventModel: EventModel): EventModel
    fun get(eventId: Long): EventModel?
    fun update(EventModel: EventModel): EventModel
    fun delete(eventId: Long)
    fun findByGeoTag(geotag: String): List<Event>
}

interface PublicationRepository : JpaRepository<PublicationModel, Long> {
    fun create(PublicationModel: PublicationModel, eventId: Long): PublicationModel
    fun get(publicationId: Long): PublicationModel?
    fun update(PublicationModel: PublicationModel): PublicationModel
    fun delete(publicationId: Long)
}

interface ReactionRepository : JpaRepository<ReactionModel, Long> {
    fun create(reactionModel: ReactionModel, publicationId: Long): ReactionModel
    fun get(reactionModelId: Long): ReactionModel?
    fun update(reactionModel: ReactionModel): ReactionModel
    fun delete(reactionModelId: Long)
}

interface CommentRepository : JpaRepository<CommentModel, Long> {
    fun create(commentModel: CommentModel, publicationId: Long): CommentModel
    fun get(commentId: Long): CommentModel?
    fun update(commentModel: CommentModel): CommentModel
    fun delete(commentId: Long)
}

interface UserRepository : JpaRepository<UserModel, Long> {
    fun create(user: UserModel): UserModel
    fun get(userId: Long): UserModel?
    fun delete(userId: Long)
}

interface FriendRepository : JpaRepository<FriendModel, Long> {


    fun findByUser_Id(id: Long): List<FriendModel>

}

interface ChatRepository : JpaRepository<ChatModel, Long> {
    fun create(chat: ChatModel): ChatModel
    fun get(chatId: Long): ChatModel?
    fun delete(chatId: Long)
}

interface MessageRepository : JpaRepository<MessageModel, Long> {
    fun create(chat: MessageModel): MessageModel
    fun get(chatId: Long): MessageModel?
    fun delete(chatId: Long)
}

interface InviteRepository : JpaRepository<InviteModel, Long> {
    fun create(invite: InviteModel): InviteModel
    fun get(inviteId: Long): InviteModel?
    fun delete(inviteId: Long)
}
