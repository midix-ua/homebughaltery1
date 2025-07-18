package com.example.demo.controller;

import com.example.demo.dto.AccountDTO;
import com.example.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    @GetMapping("/login")
    public String  showLoginForm() {
        return "login";
    }

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/account-details"; // або інша основна сторінка
    }

    @GetMapping("/account-details")
    public String showAccountDetails(Model model, Principal principal) {
        String login = principal.getName();
        var account = accountService.getRepo().findByLogin(login);
        if (account == null) return "error";

        account.getIncomes().size();
        account.getSpendings().size();
        account.getCredits().size();
        account.getCumulations().size();
        account.getTotalMoney();

        model.addAttribute("account", account);
        return "account-details";
    }

    @PostMapping("/register")
    public String registerAccount(@RequestParam String login, @RequestParam String password, @ModelAttribute AccountDTO accountDTO) {
        accountService.addAccount(login, password, accountDTO);
        return "redirect:/login";
    }


}
