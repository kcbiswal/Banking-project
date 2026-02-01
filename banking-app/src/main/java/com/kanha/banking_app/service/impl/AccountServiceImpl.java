package com.kanha.banking_app.service.impl;

import com.kanha.banking_app.Entity.Account;
import com.kanha.banking_app.dto.AccountDto;
import com.kanha.banking_app.mapper.AccountMapper;
import com.kanha.banking_app.repository.AccountRepository;
import com.kanha.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposite(Long id,double amount) {
        Account  account=accountRepository.findById(id).orElse(null);
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
       return  AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id).orElse(null);

        if (account.getBalance()<amount){
            throw new RuntimeException("indufficient balance");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account -> AccountMapper.mapToAccountDto(account))).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account not found"));
        accountRepository.deleteById(id);
    }

}
