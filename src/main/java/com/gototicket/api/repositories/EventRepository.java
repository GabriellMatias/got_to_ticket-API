package com.gototicket.api.repositories;

import com.gototicket.api.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// to create, use the entitie type and pk type
public interface EventRepository extends JpaRepository<Event, UUID> {
}
