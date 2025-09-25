package com.fahrul.rml_bank.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.fahrul.rml_bank.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByRekening(String rekening);
}
