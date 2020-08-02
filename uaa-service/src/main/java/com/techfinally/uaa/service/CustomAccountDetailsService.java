package com.techfinally.uaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.techfinally.uaa.model.entity.Account;
import com.techfinally.uaa.repository.AccountRepository;

/**
 * @author Truong Duong
 */
@Service(value = "userDetailsService")
public class CustomAccountDetailsService implements UserDetailsService {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    Account account = accountRepository.findByUsername(username);

    if (account == null) {
      throw new BadCredentialsException("Bad credentials");
    }

    new AccountStatusUserDetailsChecker().check(account);

    return account;
  }

}
