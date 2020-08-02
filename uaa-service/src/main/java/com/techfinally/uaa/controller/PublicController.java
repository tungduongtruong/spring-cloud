package com.techfinally.uaa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.techfinally.uaa.model.reponse.RestResult;

import java.util.Random;

/**
 * @author : Truong Duong
 **/
@RestController
@RequestMapping(value = "/api/public/")
public class PublicController {

  @GetMapping
  public ResponseEntity random() {
    Random random = new Random();

    RestResult result = new RestResult<>();
    result.ok(random.nextInt(100));
    return new ResponseEntity<>(result, HttpStatus.OK);
  }


}
