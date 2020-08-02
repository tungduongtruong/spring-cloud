package com.techfinally.uaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.techfinally.uaa.model.mapper.AccountMapper;
import com.techfinally.uaa.model.reponse.RestResult;
import com.techfinally.uaa.model.request.UserRequest;
import com.techfinally.uaa.service.AccountService;

/**
 * @author Truong Duong
 */
@RestController
@RequestMapping(value = "/api/v1/accounts/user")
public class UserController {

  @Autowired
  AccountService accountService;

  @Autowired
  AccountMapper accountMapper;

  @GetMapping(value = "/")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity findAll() {
    RestResult result = new RestResult<>();
    result.ok(accountMapper.toListDTO(accountService.findByAll()));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity findById(@PathVariable(value = "id") long id) {
    RestResult result = new RestResult<>();
    result.ok(accountMapper.toDTO(accountService.findById(id)));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping(value = "/")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity save(@RequestBody UserRequest userRequest) {
    RestResult result = new RestResult<>();
//    result.ok(accountMapper.toDTO(accountService.findById(accountMapper.toEntity(userRequest))));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PutMapping(value = "/")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity update(@RequestBody UserRequest userRequest) {
    RestResult result = new RestResult<>();
//    result.ok(accountMapper.toDTO(accountService.findById(id)));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

}