package com.palvelinohjelmointi.harj.service;

import com.palvelinohjelmointi.harj.entity.Event;
import com.palvelinohjelmointi.harj.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();  // Fetch all events from the database
    }

    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }

    public void createEvent(String title, String description, Date date, Time time) {
        Event event = new Event();
        event.setEventTitle(title);
        event.setEventDescription(description);
        event.setEventDate((java.sql.Date) date);
        event.setEventTime(time);

        // Save the event to the database
        eventRepository.save(event);
    }
}
