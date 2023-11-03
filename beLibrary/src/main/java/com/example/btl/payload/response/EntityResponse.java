package com.example.btl.payload.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class EntityResponse {
    private String message;
    private Object data;

    public EntityResponse(String message, Object data) {
            this.message = message;
            this.data = data;
        }
}
