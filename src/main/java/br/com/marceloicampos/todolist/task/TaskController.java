package br.com.marceloicampos.todolist.task;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/tasks")

public class TaskController {
  @Autowired
  private TaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity<?> create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    System.out.println("No TaskController " + request.getAttribute("idUser"));
    var idUser = request.getAttribute("idUser");
    taskModel.setIdUser((UUID) idUser);
    var currentDate = LocalDateTime.now();
    if (currentDate.isAfter(taskModel.getStartAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Invalid date specified - Current Date is Later than the Start Date");
    }
    if (currentDate.isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Invalid date specified - Current Date is Later than the End Date");
    }
    if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Invalid date specified - The Start Date is Later than End Date");
    }
    var task = this.taskRepository.save(taskModel);
    return ResponseEntity.status(HttpStatus.OK).body(task);
  }

  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request) {
    var idUser = request.getAttribute("idUser");
    var tasks = this.taskRepository.findByIdUser((UUID) idUser);
    return tasks;
  }

  // http://localhost:8080/tasks/9897986465897974-dadadasddds-5845458754 (example)
  @PutMapping("/{id}")
  public TaskModel update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {
    var idUser = request.getAttribute("idUser");
    taskModel.setIdUser((UUID) idUser);
    taskModel.setId(id);
    return this.taskRepository.save(taskModel);
  }
}
