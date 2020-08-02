package com.techfinally.uaa.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author : Truong Duong
 **/
@Getter
@Setter
public class RoleRequest {

  // Role
  private long id;
  private String name;
  private String description;
  private boolean enabled;

  // Permission
  private List<PermissionRequest> permissions;

}
