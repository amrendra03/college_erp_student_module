package com.server.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

   private Logger log  = LoggerFactory.getLogger(Notification.class);

   @Override
   public NotificationDto create(NotificationDto notificationDto) {

      notificationDto.setTimestamp(new Date());
      notificationDto.setActive(true);
      Notification x = this.notificationRepo.save(this.modelMapper.map(notificationDto, Notification.class));

      return this.modelMapper.map(x, NotificationDto.class);
   }

   @Override
   public void delete(Long id) {

      Optional<Notification> x = this.notificationRepo.findById(id);

      this.notificationRepo.delete(x.get());

   }

   @Override
   public List<NotificationDto> getall(int page, int pageSize) {
      log.info("Processing notification list...");
      Page<Notification> all = this.getAllNotificationsOrderByTimestampDesc(page,pageSize);
//      List<NotificationDto> x = all.stream().map((y) -> this.modelMapper.map(y, NotificationDto.class))
//            .collect(Collectors.toList());

      List<NotificationDto> notificationDtos = new ArrayList<>();

      for (Notification notification : all) {
         if (notification.isOlderThanFiveDays()) {
//            log.info("notification is older than 5 days");
            notification.setActive(true);
         }else{
//            log.info("notification is not older than 5 days ");
            notification.setActive(false);
         }

         NotificationDto dto = this.modelMapper.map(notification, NotificationDto.class);
         notificationDtos.add(dto);
      }

      return notificationDtos;
   }

   public  Page<Notification> getAllNotificationsOrderByTimestampDesc(int page, int pageSize) {
      // Create a PageRequest object to represent the requested page
//      Pageable pageable = PageRequest.of(page, pageSize, Sort.by("candidateModel.candidateId").descending());
      Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());

      // Use the repository method to get paginated notifications
      return notificationRepo.findAll(pageable);
   }

}
