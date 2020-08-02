package com.techfinally.uaa.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Truong Duong
 */
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends BaseIdEntity implements Serializable {

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "enabled")
  private boolean enabled;

  @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE )
  private List<RolePermission> permissions = new ArrayList<>();

}
