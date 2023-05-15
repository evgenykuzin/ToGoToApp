package com.jekajops.togotoapp.service.impl

import com.jekajops.togotoapp.*
import com.jekajops.togotoapp.service.EventService
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class EventServiceImpl(
    private val eventRepository: EventRepository,
    private val publicationRepository: PublicationRepository,
    private val reactionRepository: ReactionRepository,
    private val commentRepository: CommentRepository,
) : EventService {

    override fun createEvent(event: Event): Event {
        return eventRepository.save(event.toEntity()).toDto()
    }

    override fun getEvent(eventId: Long): Event {
        return eventRepository.findById(eventId).orElse(null).toDto()
    }

    override fun getEventsByGeoTag(geoTag: String): List<Event> {
        return eventRepository.findByGeoTag(geoTag)
    }

    override fun getEvents(): List<Event> {
        return eventRepository.findAll().map { it.toDto() }
    }
    override fun updateEvent(event: Event): Event {
        return eventRepository.update(event.toEntity()).toDto()
    }
    override fun deleteEvent(eventId: Long) {
        eventRepository.delete(eventId)
    }


    override fun addPublication(publication: Publication): Publication {
        val event = publication.eventId?.let { getEvent(it) } ?: throw EntityNotFoundException("Event not found")
        //publication.event = event
        return  publicationRepository.save<PublicationModel?>(publication.toEntity()).toDto()
    }

    override fun getPublication(publicationId: Long): Publication {
        return  publicationRepository.findById(publicationId).orElse(null).toDto()
    }

    override fun updatePublication(publication: Publication): Publication {
        return publicationRepository.update(publication.toEntity()).toDto()
    }

    override fun deletePublication(publicationId: Long) {
        publicationRepository.delete(publicationId)
    }

    override fun addReaction(reaction: Reaction): Reaction {
        val publication = reaction.publicationId?.let { getPublication(it) } ?: throw EntityNotFoundException("Publication not found")
        //reaction.publication = publication
        return  reactionRepository.save(reaction.toEntity()).toDto()
    }

    override fun addComment(comment: Comment): Comment {
        val publication = comment.publicationId?.let { getPublication(it) } ?: throw EntityNotFoundException("Publication not found")
        //comment.publication = publication
        return commentRepository.save(comment.toEntity()).toDto()
    }

}
