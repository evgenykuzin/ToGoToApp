package com.jekajops.togotoapp.service

import com.jekajops.togotoapp.Comment
import com.jekajops.togotoapp.Event
import com.jekajops.togotoapp.Publication
import com.jekajops.togotoapp.Reaction

interface EventService {
    fun createEvent(event: Event): Event
    fun getEvent(eventId: Long): Event?
    fun getEvents(): List<Event>
    fun updateEvent(event: Event): Event
    fun deleteEvent(eventId: Long)
    fun getEventsByGeoTag(geoTag: String): List<Event>

    fun getPublication(publicationId: Long): Publication?
    fun updatePublication(publication: Publication): Publication
    fun deletePublication(publicationId: Long)
    fun addPublication(publication: Publication): Publication

    fun addReaction(reaction: Reaction): Reaction
    fun getReaction(reactionId: Long): Reaction?
    fun updateReaction(reaction: Reaction): Reaction
    fun deleteReaction(reactionId: Long)

    fun addComment(comment: Comment): Comment
    fun getComment(commentId: Long): Comment?
    fun updateComment(comment: Comment): Comment
    fun deleteComment(commentId: Long)
}
