package com.example.btl.service;

import com.example.btl.entity.Fine;
import com.example.btl.repository.FineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FineService {
    private final FineRepository fineRepository;

    public Fine getFineByUserId(Long userId) {
        return fineRepository.findByUserId(userId);
    }
}
