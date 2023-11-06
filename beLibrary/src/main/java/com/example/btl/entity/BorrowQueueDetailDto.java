package com.example.btl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BorrowQueueDetailDto
{
    private Integer id;
    private Integer userId;
    @JsonIgnore
    private User user;
    private String position;
    private String dateBorrow;
    private String dateDue;
}
