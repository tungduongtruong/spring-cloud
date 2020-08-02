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
@Table(name = "account_role")
public class AccountRole implements Serializable {

  @EmbeddedId
  private AccountRoleId id;

  @ManyToOne
  @JoinColumn(name = "account_id")
  @MapsId("accountId")
  private Account account;

  @ManyToOne
  @JoinColumn(name = "role_id")
  @MapsId("roleId")
  private Role role;

  @Column(name = "enabled")
  private boolean enabled;

}
