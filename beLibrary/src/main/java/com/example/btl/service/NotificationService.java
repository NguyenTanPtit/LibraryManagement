package com.example.btl.service;

import com.example.btl.entity.Notification;
import com.example.btl.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public List<Notification> getAllByUserId(Long id) {
        return notificationRepository.findAllByUserId(id);
    }

    public void addNewNotification(Notification notification) {
        notificationRepository.save(notification);
    }

}
