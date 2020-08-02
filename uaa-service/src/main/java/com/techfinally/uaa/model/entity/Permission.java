package com.techfinally.uaa.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Truong Duong
 */
@Getter
@Setter
@Entity
@Table(name = "permission")
public class Permission extends BaseIdEntity implements Serializable {

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "enabled")
  private boolean enabled;

}