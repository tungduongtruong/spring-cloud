package com.techfinally.uaa.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : Truong Duong
 **/
@Getter
@Setter
public class UserRequest {

  // User
  private long id;
  private String fullName;
  private String username;
  private String password;
  private String email;
  private boolean enabled;

  // Role
  private List<RoleRequest> roles;

}