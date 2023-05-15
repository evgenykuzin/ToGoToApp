package com.jekajops.togotoapp

fun Event.toEntity(): EventModel {
    return EventModel(
        id = this.id,
        name = this.name,
        title = this.title,
        geoTag = this.geoTag,
        participants = this.participants.map { it.toEntity() },
        viewers = this.viewers.map { it.toEntity() },
        publications = this.publications.map { it.toEntity() }.toSet(),
        createdAt = this.createdAt
    )
}

fun EventModel.toDto(): Event {
    return Event(
        id = this.id,
        name = this.name,
        title = this.title,
        geoTag = this.geoTag,
        participants = this.participants.map { it.toDto() },
        viewers = this.viewers.map { it.toDto() },
        publications = this.publications.map { it.toDto() }.toSet(),
        createdAt = this.createdAt
    )
}


fun Publication.toEntity(): PublicationModel {
    return PublicationModel(
        id = this.id,
        publisherId = this.publisherId,
        publicationDateTime = this.publicationDateTime,
        hashtags = this.hashtags,
        mediaData = MediaDataModel(),
        reactions = this.reactions.map { it.toEntity() },
        comments = this.comments.map { it.toEntity() }
    )
}

fun PublicationModel.toDto(): Publication {
    return Publication(
        id = this.id,
        publisherId = this.publisherId,
        publicationDateTime = this.publicationDateTime,
        hashtags = this.hashtags,
        mediaData = MediaData,
        reactions = this.reactions.map { it.toDto() },
        comments = this.comments.map { it.toDto() }
    )
}


fun Reaction.toEntity(): ReactionModel {
    return ReactionModel(
        id = this.id,
        publisherId = this.publisherId,
        publicationId = this.publicationId,
        emojiCode = this.emojiCode,
        reactionDateTime = this.reactionDateTime
    )
}

fun ReactionModel.toDto(): Reaction {
    return Reaction(
        id = this.id,
        publisherId = this.publisherId,
        publicationId = this.publicationId,
        emojiCode = this.emojiCode,
        reactionDateTime = this.reactionDateTime
    )
}


fun Comment.toEntity(): CommentModel {
    return CommentModel(
        id = this.id,
        publisherId = this.publisherId,
        publicationId = this.publicationId,
        text = this.text,
        commentDateTime = this.commentDateTime,
        replies = this.replies.map { it.toEntity() }
    )
}

fun CommentModel.toDto(): Comment {
    return Comment(
        id = this.id,
        publisherId = this.publisherId,
        publicationId = this.publicationId,
        text = this.text,
        commentDateTime = this.commentDateTime,
        replies = this.replies.map { it.toDto() }
    )
}


fun User.toEntity(): UserModel {
    val friends = this.friends.map { it.toEntity() }
    val subscribers = this.subscribers.map { it.toEntity() }
    val events = this.events.map { it.toEntity() }
    return UserModel(
        id = this.id,
        name = this.name,
        bio = this.bio,
        geoTag = this.geoTag,
        rating = this.rating,
        friends = friends,
        subscribers = subscribers,
        events = events
    )
}

fun UserModel.toDto(): User {
    return User(
        id = this.id,
        name = this.name,
        bio = this.bio,
        geoTag = this.geoTag,
        rating = this.rating,
        friends = this.friends.map { it.toDto() },
        subscribers = this.subscribers.map { it.toDto() },
        events = this.events.map { it.toDto() }
    )
}


fun Friend.toEntity(): FriendModel {
    return FriendModel(
        id = this.id,
        user = UserModel(this.userId),
        friend = UserModel(this.friendId)
    )
}

fun FriendModel.toDto(): Friend {
    return Friend(
        id = this.id,
        userId = this.user?.id,
        friendId = this.friend?.id
    )
}


fun Chat.toEntity(): ChatModel {
    return ChatModel(
        id = this.id,
        participants = this.participants.map { it.toEntity() },
        messages = this.messages.map { it.toEntity() }
    )
}

fun ChatModel.toDto(): Chat {
    return Chat(
        id = this.id,
        participants = this.participants.map { it.toDto() },
        messages = this.messages.map { it.toDto() }
    )
}


fun Message.toEntity(): MessageModel {
    return MessageModel(
        id = this.id,
        senderId = this.senderId,
        text = this.text,
        sentDateTime = this.sentDateTime,
        chat = ChatModel(this.chatId)
    )
}

fun MessageModel.toDto(): Message {
    return Message(
        id = this.id,
        senderId = this.senderId,
        text = this.text,
        sentDateTime = this.sentDateTime,
        chatId = this.chat?.id
    )
}


fun Invite.toEntity(): InviteModel {
    return InviteModel(
        id = this.id,
        fromUserId = this.fromUserId,
        toUserId = this.toUserId,
        eventId = this.eventId
    )
}

fun InviteModel.toDto(): Invite {
    return Invite(
        id = this.id,
        fromUserId = this.fromUserId,
        toUserId = this.toUserId,
        eventId = this.eventId
    )
}





