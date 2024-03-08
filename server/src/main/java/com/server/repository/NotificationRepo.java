package com.server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.server.entities.Notification;

import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
//    List<Notification> findAllByOrderByTimestampDesc();
    Page<Notification> findAll(Pageable pageable);
}
