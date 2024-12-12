package com.palvelinohjelmointi.harj.controller;

import com.palvelinohjelmointi.harj.entity.Event;
import com.palvelinohjelmointi.harj.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/events/create")
    public String createEvent(@RequestParam String eventTitle, Model model) {
        // Create and save the event
        Event event = new Event();
        event.setTitle(eventTitle);
        eventRepository.save(event);

        // Redirect or update the model to show all events
        model.addAttribute("events", eventRepository.findAll());
        return "redirect:/";
    }
}
