package com.techfinally.uaa.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author : Truong Duong
 **/
@Embeddable
@Getter
@Setter
public class RolePermissionId implements Serializable {

  private Long roleId;
  private Long permissionId;

}
