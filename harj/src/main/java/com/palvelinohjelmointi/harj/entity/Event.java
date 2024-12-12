package com.palvelinohjelmointi.harj.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "event_title", nullable = false)
    private String eventTitle;

    @Column(name = "event_description")
    private String eventDescription;

    @Column(name = "event_date")
    private java.sql.Date eventDate;

    @Column(name = "event_time")
    private java.sql.Time eventTime;

    @Column(name = "due_date")
    private java.sql.Date dueDate;

    @ManyToMany
    @JoinTable(
            name = "event_categories",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
}
