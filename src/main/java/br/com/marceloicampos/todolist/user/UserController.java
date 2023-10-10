package br.com.marceloicampos.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

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
  @Autowired
  private UserRepository userRepository;

  @PostMapping("/")
  public UserModel create(@RequestBody UserModel UserModel) {
    var userCreated = this.userRepository.save(UserModel);
    return userCreated;
    // System.out.println(UserModel.getName());
    // System.out.println(UserModel.getUsername());
    // System.out.println(UserModel.getPassword());
  }
}
