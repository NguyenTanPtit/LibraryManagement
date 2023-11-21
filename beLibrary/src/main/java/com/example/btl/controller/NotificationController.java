package com.example.btl.controller;

import com.example.btl.entity.Notification;
import com.example.btl.payload.response.EntityResponse;
import com.example.btl.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService service;

    @PostMapping("/delete")
    public ResponseEntity<EntityResponse> deleteNotification(@RequestParam Long id) {
        try {
            service.deleteNotification(id);
            EntityResponse data = EntityResponse.builder()
                    .message("Delete notification successfully!")
                    .data(null)
                    .build();
            return ResponseEntity.ok().body(data);
        }catch (Exception e){
            EntityResponse data = EntityResponse.builder()
                    .message("Delete notification failed!")
                    .data(null)
                    .build();
            return ResponseEntity.ok().body(data);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<EntityResponse> addNotification(@RequestBody Notification request) {
        try {
            service.addNewNotification(request);
            EntityResponse data = EntityResponse.builder()
                    .message("Add notification successfully!")
                    .data(null)
                    .build();
            return ResponseEntity.ok().body(data);
        }catch (Exception e){
            EntityResponse data = EntityResponse.builder()
                    .message("Add notification failed!")
                    .data(null)
                    .build();
            return ResponseEntity.ok().body(data);
        }
    }

    @PostMapping("/getAllByUserId")
    public ResponseEntity<EntityResponse> getAllNotification(@RequestParam Long id) {
        try {
            EntityResponse data = EntityResponse.builder()
                    .message("Get all notification successfully!")
                    .data(service.getAllByUserId(id))
                    .build();
            return ResponseEntity.ok().body(data);
        }catch (Exception e){
            EntityResponse data = EntityResponse.builder()
                    .message("Get all notification failed!")
                    .data(null)
                    .build();
            return ResponseEntity.ok().body(data);
        }
    }
}
