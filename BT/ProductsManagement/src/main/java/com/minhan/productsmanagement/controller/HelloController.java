package com.minhan.productsmanagement.controller;

import com.minhan.productsmanagement.model.respone.HelloResponse1;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

  @GetMapping("/springboot")
  public ResponseEntity helloSpringboot() {
    HelloResponse1 helloResponse = new HelloResponse1();
    helloResponse.setMsg("Hello Spring boot");
    try{

    } catch (Exception e) {
      return null;
    }
    return ResponseEntity.ok(helloResponse);
  }


}
