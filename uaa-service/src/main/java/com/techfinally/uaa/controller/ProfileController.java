package com.techfinally.uaa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techfinally.uaa.model.reponse.RestResult;

/**
 * @author : Truong Duong
 **/
@RestController
@RequestMapping(value = "/api/v1/profiles")
public class ProfileController {

  @PreAuthorize("hasAnyAuthority('role_admin','role_user')")
  @GetMapping(value = "/{id}/")
  public ResponseEntity findById(@PathVariable(value = "id") long id) {
    RestResult result = new RestResult<>();
//    result.ok(accountMapper.toDTO(accountService.findById(id)));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

}
