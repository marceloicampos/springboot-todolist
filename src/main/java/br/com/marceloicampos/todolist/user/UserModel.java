package br.com.marceloicampos.todolist.user;

// import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// @Data junta Setter e junta Getter em uma só annotation
public class UserModel {
  private String name;
  private String username;
  private String password;
}
