package com.jekajops.togotoapp

import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class EventModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String? = null,
    val title: String? = null,
    val geoTag: String? = null,
    @ManyToMany
    val participants: List<UserModel> = listOf(),
    @ManyToMany
    val viewers: List<UserModel> = listOf(),
    @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL])
    val publications: Set<PublicationModel> = setOf(),
    val createdAt: LocalDateTime? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as EventModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }

}

@Entity
data class PublicationModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val publisherId: Long? = null,
    val publicationDateTime: LocalDateTime? = null,
    @ElementCollection
    val hashtags: Set<String> = setOf(),
    @Embedded
    val mediaData: MediaDataModel? = null,
    @OneToMany(mappedBy = "publication", cascade = [CascadeType.ALL])
    val reactions: List<ReactionModel> = listOf(),
    @OneToMany(mappedBy = "publication", cascade = [CascadeType.ALL])
    val comments: List<CommentModel> = listOf(),
    @ManyToOne
    val event: EventModel? = null
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as PublicationModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}

@Embeddable
sealed class MediaDataModel(
    @Embedded
    val photo: PhotoModel? = null,
    @Embedded
    val video: VideoModel? = null
) {

    @Entity
    data class PhotoModel(val url: String)
    data class VideoModel(val url: String, val durationSeconds: Int)
}

@Entity
data class ReactionModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val publisherId: Long? = null,
    val publicationId: Long? = null,
    val emojiCode: String? = null,
    val reactionDateTime: LocalDateTime? = null,
    @ManyToOne
    val publication: PublicationModel? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Reaction

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}

@Entity
data class CommentModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val publisherId: Long? = null,
    val publicationId: Long? = null,
    val text: String? = null,
    val commentDateTime: LocalDateTime? = null,
    @OneToMany(mappedBy = "parentComment", cascade = [CascadeType.ALL])
    val replies: List<CommentModel> = listOf(),
    @ManyToOne
    val publication: PublicationModel? = null,
    @ManyToOne
    val parentComment: CommentModel? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as CommentModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}

@Entity
data class UserModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String? = null,
    val bio: String? = null,
    val geoTag: String? = null,
    val rating: Double? = null,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val friends: List<FriendModel> = listOf(),
    @ManyToMany(mappedBy = "subscribers")
    val subscribers: List<UserModel> = listOf(),
    @ManyToMany(mappedBy = "viewers")
    val events: List<EventModel> = listOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as UserModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}

@Entity
data class FriendModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    val user: UserModel? = null,
    @ManyToOne
    val friend: UserModel? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as FriendModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}

@Entity
data class ChatModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToMany
    val participants: List<UserModel> = listOf(),
    @OneToMany(mappedBy = "chat", cascade = [CascadeType.ALL])
    val messages: List<MessageModel> = listOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ChatModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}

@Entity
data class MessageModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val senderId: Long? = null,
    val text: String? = null,
    val sentDateTime: LocalDateTime? = null,
    @ManyToOne
    val chat: ChatModel? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as MessageModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}

@Entity
data class InviteModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne
    val fromUserId: Long? = null,
    @ManyToOne
    val toUserId: Long? = null,
    @ManyToOne
    val eventId: Long? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as InviteModel

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}

