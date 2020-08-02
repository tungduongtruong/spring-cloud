package com.techfinally.uaa.service;

import com.techfinally.uaa.model.entity.Account;

import java.util.List;

/**
 * @author : Truong Duong
 **/
public interface AccountService {

  List<Account> findByAll();

  Account findById(long accountId);

  Account findByUsername(String username);

}
