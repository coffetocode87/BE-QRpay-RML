package com.fahrul.rml_bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fahrul.rml_bank.model.Transaksi;

public interface TransaksiRepository extends JpaRepository<Transaksi, Integer> {}