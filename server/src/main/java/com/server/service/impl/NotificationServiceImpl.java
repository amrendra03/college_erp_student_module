package com.server.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dto.NotificationDto;
import com.server.entities.Notification;
import com.server.repository.NotificationRepo;
import com.server.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

   @Autowired
   private NotificationRepo notificationRepo;

   @Autowired
   private ModelMapper modelMapper;

   @Override
   public NotificationDto create(NotificationDto notificationDto) {

      Notification x = this.notificationRepo.save(this.modelMapper.map(notificationDto, Notification.class));

      return this.modelMapper.map(x, NotificationDto.class);
   }

   @Override
   public void delete(Long id) {

      Optional<Notification> x = this.notificationRepo.findById(id);

      this.notificationRepo.delete(x.get());

   }

   @Override
   public List<NotificationDto> getall() {
      List<Notification> all = this.notificationRepo.findAll();

      List<NotificationDto> x = all.stream().map((y) -> this.modelMapper.map(y, NotificationDto.class))
            .collect(Collectors.toList());

      return x;
   }

}
