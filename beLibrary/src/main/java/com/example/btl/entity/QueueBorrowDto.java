package com.example.btl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class QueueBorrowDto {
    private Long id;
    private Long bookId;
    @JsonIgnore
    private Book book;
    private List<BorrowQueueDetailDto> borrowQueueDetailDtos;
}
