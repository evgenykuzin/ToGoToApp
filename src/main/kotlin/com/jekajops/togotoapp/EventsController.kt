package com.jekajops.togotoapp

import com.jekajops.togotoapp.service.EventService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/events")
class EventController(private val eventService: EventService) {

    @GetMapping
    fun getEvents(): List<Event> {
        return eventService.getEvents()
    }

    @GetMapping("/{eventId}")
    fun getEvent(@PathVariable eventId: Long): Event? {
        return eventService.getEvent(eventId)
    }

    @PostMapping
    fun createEvent(@RequestBody event: Event): Event {
        return eventService.createEvent(event)
    }

    @PutMapping("/{eventId}")
    fun updateEvent(@PathVariable eventId: Long, @RequestBody event: Event): Event {
        return eventService.updateEvent(eventId, event)
    }

    @DeleteMapping("/{eventId}")
    fun deleteEvent(@PathVariable eventId: Long) {
        eventService.deleteEvent(eventId)
    }

    @PostMapping("/publications")
    fun addPublication(@RequestBody publication: Publication): Publication {
        return eventService.addPublication(publication)
    }

    @PostMapping("/reactions")
    fun addReaction(@RequestBody reaction: Reaction): Reaction {
        return eventService.addReaction(reaction)
    }

    @PostMapping("/{eventId}/comments")
    fun addComment(@RequestBody comment: Comment): Comment {
        return eventService.addComment(comment)
    }

    @PostMapping("/friends")
    fun addFriend(@RequestBody friend: Friend): Friend {
        return eventService.addFriend(friend)
    }

    @PostMapping("/chat")
    fun startChat(@RequestBody chat: Chat): Chat {
        return eventService.createChat(chat)
    }

    @PostMapping("/invite")
    fun inviteFriend(@RequestBody invite: Invite): Invite {
        return eventService.inviteFriend(invite)
    }
}
