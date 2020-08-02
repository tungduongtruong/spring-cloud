package com.techfinally.uaa.model.mapper;

import org.springframework.stereotype.Component;
import com.techfinally.uaa.model.entity.Account;
import com.techfinally.uaa.model.entity.AccountRole;
import com.techfinally.uaa.model.reponse.AccountReponse;
import com.techfinally.uaa.model.request.UserRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Truong Duong
 **/
@Component
public class AccountMapper {

  public AccountReponse toDTO(Account account) {
    if (account == null) {
      return null;
    }

    AccountReponse accountReponse = new AccountReponse();
    // Account
    accountReponse.setId(account.getId());
    accountReponse.setUsername(account.getUsername());
    accountReponse.setFullName(account.getFullName());
    accountReponse.setEmail(account.getEmail());
    accountReponse.setAccountNonExpired(account.isAccountNonExpired());
    accountReponse.setAccountNonLocked(account.isAccountNonLocked());
    accountReponse.setCredentialsNonExpired(account.isCredentialsNonExpired());
    accountReponse.setEnabled(account.isEnabled());

    // Role
    List<AccountRole> roles = account.getRoles();
    if (roles != null) {
      List<String> listRole = new ArrayList<>();
      long roleId = 0;
      for (AccountRole accountRole : roles) {
        roleId = accountRole.getRole().getId();
        listRole.add(accountRole.getRole().getName());
      }
//      accountReponse.setRoleId(roleId);
//      accountReponse.setRoles(listRole);
    }

    return accountReponse;
  }

  public List<AccountReponse> toListDTO(List<Account> accounts) {
    if (accounts == null) {
      return null;
    }

    List<AccountReponse> accountReponses = new ArrayList<>();
    for (Account account : accounts) {
      accountReponses.add(toDTO(account));
    }

    return accountReponses;
  }

  public Account toEntity(UserRequest userRequest) {
    if (userRequest == null) {
      return null;
    }

    Account account = new Account();
    account.setId(userRequest.getId());
    account.setUsername(userRequest.getUsername());
    account.setFullName(userRequest.getFullName());
    account.setEmail(userRequest.getEmail());
    account.setEnabled(userRequest.isEnabled());

    return account;
  }

}
