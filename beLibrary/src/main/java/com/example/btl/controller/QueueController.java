package com.example.btl.controller;

import com.example.btl.entity.*;
import com.example.btl.payload.mapper.QueueBorrowDetailMapper;
import com.example.btl.payload.mapper.QueueBorrowMapper;
import com.example.btl.payload.response.EntityResponse;
import com.example.btl.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/queue")
@RequiredArgsConstructor
public class QueueController {
    private final QueueService queueService;
    @PostMapping("/getQueueByBookId")
    public ResponseEntity<EntityResponse> get(@RequestParam Long id) {
        QueueBorrow queueBorrow = queueService.checkQueueExistByBookId(id);
        QueueBorrowDto queueBorrowDto = QueueBorrowMapper.MAPPER.mapToQueueBorrowDto(queueBorrow);
        List<BorrowQueueDetail> list1 = queueBorrow.getBorrowQueueDetails();
        List<BorrowQueueDetailDto> list = new LinkedList<>();
        list1.stream().forEach(item -> {
            list.add(QueueBorrowDetailMapper.MAPPER.mapToBorrowQueueDetailDto(item));
        });
        queueBorrowDto.setBorrowQueueDetailDtos(list);
        EntityResponse apiResponse = EntityResponse.builder()
                .message("success")
                .data(queueBorrowDto)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/joinQueue")
    public ResponseEntity<EntityResponse> joinQueue(
            @RequestParam Long userId,
            @RequestParam Long bookId,
            @RequestParam String dateBorrow,
            @RequestParam String dateDue,
            @RequestParam String position
    ) {
        try {
            QueueBorrow q = queueService.checkQueueExistByBookId(bookId);
            User u = queueService.getUserById(userId);
            BorrowQueueDetail queueBorrow = BorrowQueueDetail.builder()
                    .dateBorrow(dateBorrow)
                    .dateDue(dateDue)
                    .position(position)
                    .queueBorrowId(q)
                    .user(u)
                    .build();
            queueService.addQueueDetail(queueBorrow);
            EntityResponse entityResponse = EntityResponse.builder()
                    .message("Join queue success!")
                    .data(null)
                    .build();
            return ResponseEntity.ok(entityResponse);
        }catch (Exception e){
            EntityResponse entityResponse = EntityResponse.builder()
                    .message("Join queue fail!")
                    .data(null)
                    .build();
            return ResponseEntity.ok(entityResponse);
        }
    }
}
