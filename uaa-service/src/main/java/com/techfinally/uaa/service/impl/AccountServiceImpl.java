package com.techfinally.uaa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techfinally.uaa.model.entity.Account;
import com.techfinally.uaa.model.mapper.AccountMapper;
import com.techfinally.uaa.repository.AccountRepository;
import com.techfinally.uaa.service.AccountService;

import java.util.List;
import java.util.Optional;

/**
 * @author : Truong Duong
 **/
@Service
public class AccountServiceImpl implements AccountService {

  @Autowired
  AccountRepository accountRepository;

  @Autowired
  AccountMapper accountMapper;

  @Override
  public List<Account> findByAll() {
    return accountRepository.findAll();
  }

  @Override
  public Account findById(long accountId) {
    Optional<Account> optional = accountRepository.findById(accountId);
    return optional.isPresent() ? optional.get() : null;
  }

  @Override
  public Account findByUsername(String username) {
    return accountRepository.findByUsername(username);
  }

}
