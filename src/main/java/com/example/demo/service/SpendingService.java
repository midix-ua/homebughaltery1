package com.example.demo.service;

import com.example.demo.dto.SpendingDTO;
import com.example.demo.model.Account;
import com.example.demo.model.Spending;
import com.example.demo.repo.AccountRepo;
import com.example.demo.repo.SpendingRepo;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class SpendingService {
    private final SpendingRepo spendingRepo;
    public SpendingService(SpendingRepo spendingRepo) {
        this.spendingRepo = spendingRepo;
    }

    public SpendingRepo getSpendingRepo() {
        return spendingRepo;
    }

    public List<Spending> getSpendingsByAccountLogin(String login) {
        return spendingRepo.findByAccount_Login(login);
    }

    @Transactional
    public void addSpending(SpendingDTO spendingDTO, SpendingRepo spendingRepo,String login, AccountRepo accountRepo) {
        Account account = accountRepo.findByLogin(login);
        if(account != null){
            Spending spending  = new Spending();
            spending.setAccount(account);
            spending.setMonthly_spending(spendingDTO.getMonthly_Spending());
            spending.setDate(LocalDate.now());
            spendingRepo.save(spending);
        } else{
            throw new IllegalArgumentException("Account with login"+login+" not found");
        }
    }

    @Transactional
    public void deleteSpending(String login, SpendingRepo spendingRepo, Long id) {
        Spending spending = spendingRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Spending with id " + id + " not found"));

        if (!spending.getAccount().getLogin().equals(login)) {
            throw new AccessDeniedException("Ви не маєте доступу до цієї витрати.");
        }

        spendingRepo.delete(spending);
    }

}
