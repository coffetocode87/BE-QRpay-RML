package com.fahrul.rml_bank.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fahrul.rml_bank.model.Account;
import com.fahrul.rml_bank.model.Transaksi;
import com.fahrul.rml_bank.repository.AccountRepository;
import com.fahrul.rml_bank.repository.TransaksiRepository;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final AccountRepository accountRepo;
    private final TransaksiRepository transaksiRepo;

    public TransactionService(AccountRepository accountRepo, TransaksiRepository transaksiRepo) {
        this.accountRepo = accountRepo;
        this.transaksiRepo = transaksiRepo;
    }

    @Transactional
    public void transfer(Integer accountIdPengirim, String rekeningPengirim,
                         String rekeningPenerima, Double jumlah) {

        if (jumlah <= 0) throw new RuntimeException("Jumlah transfer harus > 0");

        Account pengirim = accountRepo.findById(accountIdPengirim)
                .orElseThrow(() -> new RuntimeException("Pengirim tidak ditemukan"));

        if (!pengirim.getRekening().equals(rekeningPengirim)) {
            throw new RuntimeException("Rekening pengirim tidak sesuai dengan account_id");
        }

        Account penerima = accountRepo.findByRekening(rekeningPenerima)
                .orElseThrow(() -> new RuntimeException("Penerima tidak ditemukan"));

        if (pengirim.getSaldo() < jumlah) {
            throw new RuntimeException("Saldo tidak cukup");
        }

        // update saldo
        pengirim.setSaldo(pengirim.getSaldo() - jumlah);
        penerima.setSaldo(penerima.getSaldo() + jumlah);
        accountRepo.save(pengirim);
        accountRepo.save(penerima);

        // simpan transaksi keluar
        transaksiRepo.save(Transaksi.builder()
                .nomorRekening(rekeningPengirim)
                .jnsTransaksi("PAYMENT")
                .jumlahTransaksi(jumlah)
                .tglTransaksi(LocalDateTime.now())
                .tujuanTransaksi(rekeningPenerima)
                .accountId(pengirim.getAccountId())
                .build());

        // simpan transaksi masuk
        transaksiRepo.save(Transaksi.builder()
                .nomorRekening(rekeningPenerima)
                .jnsTransaksi("RECEIVED")
                .jumlahTransaksi(jumlah)
                .tglTransaksi(LocalDateTime.now())
                .tujuanTransaksi(rekeningPengirim)
                .accountId(penerima.getAccountId())
                .build());
    }
}
