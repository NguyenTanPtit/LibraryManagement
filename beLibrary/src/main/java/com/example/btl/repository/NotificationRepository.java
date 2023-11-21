package com.example.btl.repository;

import com.example.btl.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("select n from Notification n where n.userId = :id")
    List<Notification> findAllByUserId(Long id);


}
