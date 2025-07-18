package com.example.demo.repo;

import com.example.demo.model.Account;
import com.example.demo.model.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpendingRepo extends JpaRepository<Spending, Long> {
    boolean existsByAccount(Account account);
    boolean existsById(Long id);
    Spending findByAccount(Account account);
    List<Spending> findByAccount_Login(String login);
}
