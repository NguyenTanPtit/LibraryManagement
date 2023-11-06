package com.example.btl.controller;


import com.example.btl.entity.Fine;
import com.example.btl.entity.FineUserDTO;
import com.example.btl.payload.response.EntityResponse;
import com.example.btl.service.FineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/api/fineController")
@RequiredArgsConstructor
public class FineController {

    private final FineService fineService;
    @PostMapping("/getFineByUserId")
    public ResponseEntity<EntityResponse> getFineByUserId(@RequestParam Long userId) {
        try {
            Fine fine = fineService.getFineByUserId(userId);
            if(fine == null){
                EntityResponse entityResponse = EntityResponse.builder()
                        .message("User don't have fine!")
                        .data(null)
                        .build();
                return ResponseEntity.ok(entityResponse);
            }else {
                FineUserDTO fineUserDTO = FineUserDTO.builder()
                        .fineId(fine.getId())
                        .userId(fine.getUser().getId().longValue())
                        .username(fine.getUser().getUsername())
                        .damageBook(fine.getDamageBook())
                        .missingBorrow(fine.getMissingBorrow())
                        .lateReturn(fine.getLateReturn())
                        .build();
                EntityResponse entityResponse = EntityResponse.builder()
                        .message(null)
                        .data(fineUserDTO)
                        .build();
                return ResponseEntity.ok(entityResponse);
//                return EntityResponse.builder()
//                        .message(null)
//                        .data(fineUserDTO)
//                        .build();
            }
        }catch (Exception e){
            EntityResponse entityResponse =  EntityResponse.builder()
                    .message("Some thing wrong! Please try again later!")
                    .data(null)
                    .build();
            return ResponseEntity.ok(entityResponse);
        }
    }
}
