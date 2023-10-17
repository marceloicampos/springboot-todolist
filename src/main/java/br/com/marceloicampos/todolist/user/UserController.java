package br.com.marceloicampos.todolist.user;

import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ResponseEntity<?> create(@RequestBody UserModel userModel) {
    var user = this.userRepository.findByUsername(userModel.getUsername());
    if (user != null) {
      System.out.println("User Already exists");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already exists");
      // Retorna o status code e a mensagem de erro
      // Response Entity
    }
    var passwordHashed = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
    // https://github.com/patrickfav/bcrypt
    userModel.setPassword(passwordHashed);
    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    // System.out.println(UserModel.getName());
    // System.out.println(UserModel.getUsername());
    // System.out.println(UserModel.getPassword());
  }

  @GetMapping("/")
  public List<UserModel> list() {
    var users = this.userRepository.findAll();
    return users;
  }
}
