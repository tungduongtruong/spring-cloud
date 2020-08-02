package com.techfinally.uaa.model.reponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : Truong Duong
 **/
@Getter
@Setter
public class AccountReponse {

  // Account
  private long id;
  private String fullName;
  private String username;
  private String password;
  private String email;
  private boolean enabled;
  private boolean accountNonLocked;
  private boolean accountNonExpired;
  private boolean credentialsNonExpired;

  // Role
  private List<RoleReponse> roles;

}
