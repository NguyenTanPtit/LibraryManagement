package com.example.btl.service;

import com.example.btl.entity.*;
import com.example.btl.payload.mapper.BookMapper;
import com.example.btl.payload.request.CallCardRequest;
import com.example.btl.payload.response.CallCardResponse;
import com.example.btl.repository.CallCardDetailRepository;
import com.example.btl.repository.CallCardRepository;
import com.example.btl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CallCardService {

    private final CallCardRepository callCardRepository;

    private final CallCardDetailRepository callCardDetailService;

    private final UserRepository userRepository;
    private final BookService bookService;

    public void deleteCallCard(Long id) {
        callCardRepository.deleteById(id);
        callCardDetailService.deleteAllByCallCardId(id.toString());
    }

    public void addCallCard(CallCardRequest request) {
        User user = userRepository.findById(request.getUserId().intValue()).get();
        CallCard callCard = CallCard.builder()
                .borrowDate(request.getBorrowDate())
                .dueDate(request.getDueDate())
                .user(user)
                .note(request.getNote())
                .state(request.getState())
                .build();
        CallCard c = callCardRepository.save(callCard);
        request.getBooks().forEach(book -> {
            CallCardDetail callCardDetail = CallCardDetail.builder()
                    .bookId(Long.valueOf(book.getId()))
                    .callCardId(String.valueOf(c.getId()))
                    .build();
            bookService.updateState(Long.valueOf(book.getId()), "Borrowed");
            callCardDetailService.save(callCardDetail);
        });

    }

    public void updateCallCard(CallCardRequest request) {
        User user = userRepository.findById(request.getUserId().intValue()).get();
        CallCard callCard = CallCard.builder()
                .id(request.getId())
                .borrowDate(request.getBorrowDate())
                .dueDate(request.getDueDate())
                .user(user)
                .note(request.getNote())
                .state(request.getState())
                .build();
        callCardRepository.save(callCard);
        callCardDetailService.deleteAllByCallCardId(request.getId().toString());
        request.getBooks().forEach(book -> {
            CallCardDetail callCardDetail = CallCardDetail.builder()
                    .bookId(Long.valueOf(book.getId()))
                    .callCardId(String.valueOf(callCard.getId()))
                    .build();
            callCardDetailService.save(callCardDetail);
        });
    }

    public List<CallCardResponse> getAll() {
        List<CallCardResponse> callCardRequests = new LinkedList<>();
        List<CallCard> callCards = callCardRepository.findAll();
        callCards.forEach(callCard -> {
            List<Book> books = callCardDetailService.getAllBookByCallCard_Id(callCard.getId());
            List<BookDto> bookDtos = new LinkedList<>();
            books.forEach(book -> {
                bookDtos.add(BookMapper.Mapper.mapToDto(book));
            });
            User user = userRepository.findById(callCard.getUser().getId()).get();
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .address(user.getAddress())
                    .fullName(user.getFullName())
                    .dateOfBirth(user.getDateOfBirth())
                    .avatar(user.getAvatar())
                    .role(String.valueOf(user.getRole()))
                    .build();
            callCardRequests.add(CallCardResponse.builder()
                    .id(callCard.getId())
                    .borrowDate(callCard.getBorrowDate())
                    .dueDate(callCard.getDueDate())
                    .state(callCard.getState())
                    .note(callCard.getNote())
                    .user(userDTO)
                    .books(bookDtos)
                    .build());
        });
       return callCardRequests;
    }

    public List<CallCardResponse> getAllByUserId(Long id) {
        List<CallCardResponse> callCardRequests = new LinkedList<>();
        List<CallCard> callCards = callCardRepository.getAllByUserId(id);
        callCards.forEach(callCard -> {
            List<Book> books = callCardDetailService.getAllBookByCallCard_Id(callCard.getId());
            List<BookDto> bookDtos = new LinkedList<>();
            books.forEach(book -> {
                bookDtos.add(BookMapper.Mapper.mapToDto(book));
            });
            User user = userRepository.findById(callCard.getUser().getId().intValue()).get();
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .address(user.getAddress())
                    .fullName(user.getFullName())
                    .dateOfBirth(user.getDateOfBirth())
                    .avatar(user.getAvatar())
                    .role(String.valueOf(user.getRole()))
                    .build();
            callCardRequests.add(CallCardResponse.builder()
                    .id(callCard.getId())
                    .borrowDate(callCard.getBorrowDate())
                    .dueDate(callCard.getDueDate())
                    .state(callCard.getState())
                    .note(callCard.getNote())
                    .user(userDTO)
                    .books(bookDtos)
                    .build());
        });
       return callCardRequests;
    }
    public List<CallCardResponse> getAllByBookId(Long id) {
        List<CallCardResponse> callCardRequests = new LinkedList<>();
        List<CallCard> callCards = callCardRepository.getAllByBookId(id);
        callCards.forEach(callCard -> {
            List<Book> books = callCardDetailService.getAllBookByCallCard_Id(callCard.getId());
            List<BookDto> bookDtos = new LinkedList<>();
            books.forEach(book -> {
                bookDtos.add(BookMapper.Mapper.mapToDto(book));
            });
            User user = userRepository.findById(callCard.getUser().getId()).get();
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .phoneNumber(user.getPhoneNumber())
                    .address(user.getAddress())
                    .fullName(user.getFullName())
                    .dateOfBirth(user.getDateOfBirth())
                    .avatar(user.getAvatar())
                    .role(String.valueOf(user.getRole()))
                    .build();
            callCardRequests.add(CallCardResponse.builder()
                    .id(callCard.getId())
                    .borrowDate(callCard.getBorrowDate())
                    .dueDate(callCard.getDueDate())
                    .state(callCard.getState())
                    .note(callCard.getNote())
                    .user(userDTO)
                    .books(bookDtos)
                    .build());
        });
       return callCardRequests;
    }
}
