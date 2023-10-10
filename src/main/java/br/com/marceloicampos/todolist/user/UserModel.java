package br.com.marceloicampos.todolist.user;

// import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Setter
@Getter
// @Data junta Setter e junta Getter em uma só annotation
@Entity(name = "tb_users")
public class UserModel {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private String name;
  private String username;
  private String password;
  @CreationTimestamp
  private LocalDateTime createdAt;
}
