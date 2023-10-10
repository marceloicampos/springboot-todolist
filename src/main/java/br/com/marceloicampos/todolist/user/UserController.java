package br.com.marceloicampos.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 ** Modificadores: public, private, protected
 ** Tipos: class, interface, enum, static, boolean
 */

@RestController
@RequestMapping("/users")
// http://localhost:8080/users/
public class UserController {
  /**
   ** Tipos Primitivos: string (texto), integer (inteiros), double (decimais),
   * float (decimais), char (caracteres), date (data), void (vazio)
   */
  @PostMapping("/")
  public void create(@RequestBody UserModel UserModel) {
    System.out.println(UserModel.getName());
    System.out.println(UserModel.getUsername());
    System.out.println(UserModel.getPassword());
  }
}
