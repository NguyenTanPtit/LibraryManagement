package com.example.btl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import com.example.btl.entity.ERole;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String avatar;
    private String password;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String address;
    private String dateOfBirth;
    @Enumerated(EnumType.STRING)
    private ERole role;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<BorrowQueueDetail> borrowQueueDetails;
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Fine fine;


}
