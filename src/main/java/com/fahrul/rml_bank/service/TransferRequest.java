package com.fahrul.rml_bank.service;

public record TransferRequest(
        String nomor_rekening_pengirim,
        String nomor_rekening_penerima,
        Double jumlah_transfer,
        String tgl_transaksi,
        Integer account_id_pengirim) {}
