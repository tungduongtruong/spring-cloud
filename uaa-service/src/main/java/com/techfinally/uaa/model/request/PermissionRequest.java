package com.techfinally.uaa.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : Truong Duong
 **/
@Getter
@Setter
@ToString
public class PermissionRequest {

  private long id;
  private String name;
  private String description;
  private boolean enabled;

}