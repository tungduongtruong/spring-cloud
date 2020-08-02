package com.techfinally.uaa.model.reponse;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : Truong Duong
 **/
@Getter
@Setter
public class RoleReponse {

  // Role
  private long id;
  private String name;
  private String description;
  private boolean enabled;

  // Permission
  private List<PermissionReponse> permissions;

  public RoleReponse() {
  }

  public RoleReponse(Long id, String name, String description, boolean enabled) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.enabled = enabled;
  }

}
