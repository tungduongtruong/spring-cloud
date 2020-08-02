package com.techfinally.uaa.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Truong Duong
 */
@Getter
@Setter
@Entity
@Table(name = "role_permission")
public class RolePermission implements Serializable {

  @EmbeddedId
  private RolePermissionId id;

  @ManyToOne
  @JoinColumn(name = "role_id")
  @MapsId("roleId")
  private Role role;

  @ManyToOne
  @JoinColumn(name = "permission_id")
  @MapsId("permissionId")
  private Permission permission;

  @Column(name = "enabled")
  private boolean enabled;

}
