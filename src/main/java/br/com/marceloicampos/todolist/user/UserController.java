package br.com.marceloicampos.todolist.user;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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
  public ResponseEntity<?> create(@RequestBody UserModel UserModel) {
    var user = this.userRepository.findByUsername(UserModel.getUsername());
    if (user != null) {
      System.out.println("User Already exists");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already exists");
      // Retorna o status code e a mensagem de erro
      // Response Entity
    }
    var userCreated = this.userRepository.save(UserModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    // System.out.println(UserModel.getName());
    // System.out.println(UserModel.getUsername());
    // System.out.println(UserModel.getPassword());
  }
}
