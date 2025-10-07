package com.kanha.banking_app.service.impl;

import com.kanha.banking_app.Entity.Account;
import com.kanha.banking_app.dto.AccountDto;
import com.kanha.banking_app.mapper.AccountMapper;
import com.kanha.banking_app.repository.AccountRepository;
import com.kanha.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
