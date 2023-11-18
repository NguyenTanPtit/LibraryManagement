package com.example.btl.controller;

import com.example.btl.payload.request.CallCardRequest;
import com.example.btl.payload.response.EntityResponse;
import com.example.btl.service.CallCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/api/card")
@RequiredArgsConstructor
public class CallCardController {

    private final CallCardService service;

    @GetMapping("/getAll")
    public ResponseEntity<EntityResponse> getAllCallCard() {
        try {
            EntityResponse data = EntityResponse.builder()
                    .message("Get all call card successfully!")
                    .data(service.getAll())
                    .build();
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<EntityResponse> deleteCallCard(@RequestParam Long id) {
        try {
            service.deleteCallCard(id);
            EntityResponse data = EntityResponse.builder()
                    .message("Delete call card successfully!")
                    .data(null)
                    .build();
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<EntityResponse> addCallCard(@RequestBody CallCardRequest request) {
        try {
            service.addCallCard(request);
            EntityResponse data = EntityResponse.builder()
                    .message("Add call card successfully!")
                    .data(null)
                    .build();
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<EntityResponse> updateCallCard(@RequestBody CallCardRequest request) {
        try {
            service.updateCallCard(request);
            EntityResponse data = EntityResponse.builder()
                    .message("Update call card successfully!")
                    .data(null)
                    .build();
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
}
