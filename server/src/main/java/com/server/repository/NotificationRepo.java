package com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.entities.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
}
