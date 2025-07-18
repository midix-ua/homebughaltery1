package com.example.demo.controller;

import com.example.demo.dto.SpendingDTO;
import com.example.demo.model.Spending;
import com.example.demo.repo.AccountRepo;
import com.example.demo.service.SpendingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class SpendingController {

    private final SpendingService spendingService;
    private final AccountRepo accountRepo;

    public SpendingController(SpendingService spendingService, AccountRepo accountRepo) {
        this.spendingService = spendingService;
        this.accountRepo = accountRepo;
    }

    @GetMapping("/addSpending")
    public String addSpending() {
        return "addSpending";
    }

    @GetMapping("/spendingList")
    public String spendingList(Model model, Principal principal) {
        String login = principal.getName();
        List<Spending> spendings = spendingService.getSpendingRepo().findByAccount_Login(login);
        model.addAttribute("spendings", spendings);
        return "spendingList";
    }

    @PostMapping("/addSpending")
    public String addSpending(@ModelAttribute SpendingDTO spendingDTO, Principal principal) {
        spendingService.addSpending(spendingDTO, spendingService.getSpendingRepo(), principal.getName(), accountRepo);
        return "redirect:/spendingList";
    }

    @PostMapping("/spendingList/delete/{id}")
    public String deleteSpending(@PathVariable("id") Long id, Principal principal) {
        String login = principal.getName();
        spendingService.deleteSpending(login, spendingService.getSpendingRepo(), id);
        return "redirect:/spendingList";
    }
}