package com.fahrul.rml_bank.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;

    private String username;
    private String email;
    private String password;
    private String rekening;
    private Double saldo;
}