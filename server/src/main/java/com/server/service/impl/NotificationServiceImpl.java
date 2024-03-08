package com.server.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
   public List<NotificationDto> getall(int page, int pageSize) {
      Page<Notification> all = this.getAllNotificationsOrderByTimestampDesc(page,pageSize);
      List<NotificationDto> x = all.stream().map((y) -> this.modelMapper.map(y, NotificationDto.class))
            .collect(Collectors.toList());

      return x;
   }

   public  Page<Notification> getAllNotificationsOrderByTimestampDesc(int page, int pageSize) {
      // Create a PageRequest object to represent the requested page
//      Pageable pageable = PageRequest.of(page, pageSize, Sort.by("candidateModel.candidateId").descending());
      Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());

      // Use the repository method to get paginated notifications
      return notificationRepo.findAll(pageable);
   }

}
