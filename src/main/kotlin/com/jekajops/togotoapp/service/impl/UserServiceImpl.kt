package com.jekajops.togotoapp.service.impl

import com.jekajops.togotoapp.*
import com.jekajops.togotoapp.service.UserService
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val friendRepository: FriendRepository,
    private val chatRepository: ChatRepository,
    private val inviteRepository: InviteRepository
) : UserService {


    override fun getUser(userId: Long): User {
        return userRepository.findById(userId).orElse(null).toDto()
    }

    override fun getFriends(userId: Long): List<User?> {
        return friendRepository.findByUser_Id(userId).map { it.friend?.toDto() }
    }

    override fun addFriend(friend: Friend): Friend {
        return friendRepository.save(friend.toEntity()).toDto()
    }

    override fun getFriend(friendId: Long): Friend {
        return friendRepository.getOne(friendId).toDto()
    }

    override fun deleteFriend(friendId: Long) {
        friendRepository.deleteById(friendId)
    }

    override fun createInvite(invite: Invite): Invite {
        return inviteRepository.save(invite.toEntity()).toDto()
    }

    override fun getInvite(inviteId: Long): Invite? {
        return inviteRepository.get(inviteId)?.toDto()
    }

    override fun deleteInvite(inviteId: Long) {
        inviteRepository.delete(inviteId)
    }

    override fun getSubscribers(userId: Long): List<User> {
        return userRepository.findById(userId)
            .map { user ->
                user.subscribers
                    .map { it.toDto() }
            }
            .orElse(emptyList())
    }

}