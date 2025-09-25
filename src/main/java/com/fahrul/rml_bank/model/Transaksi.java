package com.fahrul.rml_bank.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaksi")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transaksi {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transId;

    private String nomorRekening;
    private String jnsTransaksi;
    private Double jumlahTransaksi;
    private LocalDateTime tglTransaksi;
    private String tujuanTransaksi;
    private Integer accountId;
}
