package com.example.btl.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BorrowQueueDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "queue_borrow_id")
    @JsonIgnore
    private QueueBorrow queueBorrowId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String position;
    private String dateBorrow;
    private String dateDue;
}
