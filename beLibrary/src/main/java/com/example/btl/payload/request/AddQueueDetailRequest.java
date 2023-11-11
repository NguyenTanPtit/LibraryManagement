package com.example.btl.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddQueueDetailRequest {
    private Long bookId;
    private Long userId;
    private String position;
    private String dateBorrow;
    private String dateDue;
}