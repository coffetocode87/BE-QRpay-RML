package com.fahrul.rml_bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fahrul.rml_bank.service.TransactionService;
import com.fahrul.rml_bank.service.TransferRequest;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService txService;

    public TransactionController(TransactionService txService) {
        this.txService = txService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest req) {
        try {
            txService.transfer(
                    req.account_id_pengirim(),
                    req.nomor_rekening_pengirim(),
                    req.nomor_rekening_penerima(),
                    req.jumlah_transfer()
            );
            return ResponseEntity.ok("Transfer sukses");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
