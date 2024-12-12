package com.palvelinohjelmointi.harj.controller;

import com.palvelinohjelmointi.harj.entity.Event;
import com.palvelinohjelmointi.harj.service.EventService;
import com.palvelinohjelmointi.harj.entity.Users;
import com.palvelinohjelmointi.harj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.palvelinohjelmointi.harj.dto.EventForm;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class ThymeleafController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventService eventService;

    // Login page and handling
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Retrieve the user from the database based on username
        Users user = userRepository.findByUsername(username).orElse(null);

        // If the user exists and the password matches
        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/"; // Redirect to the home page
        } else {
            return "login"; // If login fails, show login page again
        }
    }

    // Home page: Displaying all events
    @GetMapping("/")
    public String home(Model model) {
        // Fetch all events
        List<Event> events = eventService.getAllEvents();
        // Add events to the model so they can be accessed in the view
        model.addAttribute("events", events);
        return "index";  // The name of the HTML template (index.html)
    }

    @PostMapping("/events/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEventById(id); // Call the service to delete the event
        return "redirect:/"; // Refresh the home page
    }

    @PostMapping("/events/create")
    public String createEvent(@ModelAttribute EventForm eventForm) {
        // Convert eventTime (String) to java.sql.Time
        try {
            String timeString = eventForm.getEventTime();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            java.util.Date parsedTime = sdf.parse(timeString);
            Time eventTime = new Time(parsedTime.getTime()); // convert to java.sql.Time

            eventService.createEvent(
                    eventForm.getEventTitle(),
                    eventForm.getEventDescription(),
                    eventForm.getEventDate(),
                    eventTime // Pass the java.sql.Time
            );
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception, e.g. show an error message
        }

        return "redirect:/"; // Redirect to the homepage after creating the event
    }
}
