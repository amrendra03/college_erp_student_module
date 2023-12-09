package com.server.service;

import java.util.List;

import com.server.dto.NotificationDto;

public interface NotificationService {

   NotificationDto create(NotificationDto notificationDto);

   void delete(Long id);

   List<NotificationDto> getall();

}
