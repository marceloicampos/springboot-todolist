package br.com.marceloicampos.todolist.task;

import lombok.Data;
import java.util.UUID;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  @Column(length = 50)
  private String title;
  private String description;
  private LocalDateTime startAt;
  private LocalDateTime endAt;
  private String priority;
  @CreationTimestamp
  private LocalDateTime createdAt;
  private UUID idUser;

  public void setTitle(String title) throws Exception {
    if (title.length() > 50) {
      throw new Exception("ERROR: Title is too long, max 50 characters");
    }
    this.title = title;
  }

  public void getCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
