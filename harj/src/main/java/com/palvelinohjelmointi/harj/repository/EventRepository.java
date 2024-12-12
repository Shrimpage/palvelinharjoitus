package com.palvelinohjelmointi.harj.repository;

import com.palvelinohjelmointi.harj.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
