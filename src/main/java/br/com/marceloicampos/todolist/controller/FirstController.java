package br.com.marceloicampos.todolist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/firstRouter")
public class FirstController {
  @GetMapping("/firstMethod")
  // http://localhost:8080/firstRouter/firstMethod
  public String firstString() {
    return "Funcionou";
  }
}
