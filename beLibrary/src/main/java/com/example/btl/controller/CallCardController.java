package com.example.btl.controller;

import com.example.btl.payload.request.CallCardRequest;
import com.example.btl.payload.response.EntityResponse;
import com.example.btl.service.CallCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
            EntityResponse data = EntityResponse.builder()
                    .message("Add call card failed!")
                    .data(null)
                    .build();
            return ResponseEntity.ok().body(data);
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
            System.out.println(Arrays.toString(e.getStackTrace()));
            EntityResponse data = EntityResponse.builder()
                    .message("Update call card failed!")
                    .data(null)
                    .build();
            return ResponseEntity.ok().body(data);
        }
    }

    @PostMapping("/getByUserId")
    public ResponseEntity<EntityResponse> getByUserId(@RequestParam Long id) {
        try {
            EntityResponse data = EntityResponse.builder()
                    .message("Get call card by user id successfully!")
                    .data(service.getAllByUserId(id))
                    .build();
            return ResponseEntity.ok(data);
        }catch (Exception e){
            EntityResponse data = EntityResponse.builder()
                    .message("Get call card by user id successfully!")
                    .data(null)
                    .build();
            return ResponseEntity.ok().body(data);
        }
    }

    @PostMapping("/getByBookId")
    public ResponseEntity<EntityResponse> getByBookId(@RequestParam Long id) {
        try {
            EntityResponse data = EntityResponse.builder()
                    .message("Get call card by book id successfully!")
                    .data(service.getAllByBookId(id))
                    .build();
            return ResponseEntity.ok(data);
        }catch (Exception e){
            EntityResponse data = EntityResponse.builder()
                    .message("Failed to get call card by book id!")
                    .data(null)
                    .build();
            return ResponseEntity.ok().body(data);
        }
    }
}
