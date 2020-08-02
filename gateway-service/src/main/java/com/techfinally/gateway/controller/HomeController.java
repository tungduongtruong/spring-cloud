package com.techfinally.gateway.controller;

import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @GetMapping(value = "/")
  public String index() {
    return "This is gateway-service... >> " + new Date().toString();
  }

}