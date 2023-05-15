package com.jekajops.togotoapp.service

import com.jekajops.togotoapp.Friend
import com.jekajops.togotoapp.Invite
import com.jekajops.togotoapp.User

interface UserService {
    fun getUser(userId: Long): User

    fun getFriends(userId: Long): List<User?>
    fun addFriend(friend: Friend): Friend
    fun getFriend(friendId: Long): Friend?
    fun deleteFriend(friendId: Long)

    fun getSubscribers(userId: Long): List<User>

    fun createInvite(invite: Invite): Invite
    fun getInvite(inviteId: Long): Invite?
    fun deleteInvite(inviteId: Long)
}