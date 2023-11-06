package com.example.btl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FineUserDTO {
    private Long fineId;
    @JsonIgnore
    private User user;
    private String username;
    private Long userId;
    private int missingBorrow;
    private int lateReturn;
    private int damageBook;

}
