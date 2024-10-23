package kg.adyl.fronttodolist;

import kg.adyl.fronttodolist.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class Controller {
    private final  UserService userService;
    private final TaskService taskService;


    @PostMapping("/login")
    public LoginEntity login(@RequestBody RegisterEntity user) {
        User logined = userService.login(user);
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setId(logined.getId());
        loginEntity.setUserName(logined.getUserName());
        loginEntity.setPassword(logined.getPassword());
        return loginEntity;
    }


    @PostMapping("/register")
    public RegisterEntity register(@RequestBody RegisterEntity user) {
        User registered = userService.register(user);
        return user;
    }

    @PostMapping("/addTask/{userId}")
    public Task addTask(@PathVariable Long userId, @RequestBody TaskDto task) {
        Task added = taskService.addTask(userId, task);
        return added;
    }

    @PostMapping("/editTask/{userId}/{id}")
    public Task editTask(@PathVariable Long userId, @PathVariable Long id, @RequestBody TaskDto text) {
        Task edited = taskService.editTask(userId, id, text);
        return edited;
    }

    @PostMapping("/moveToStatus/{userId}/{id}/{status}")
    public Task moveTaskToStatus(@PathVariable Long userId, @PathVariable Long id, @PathVariable String status) {
        Task movedTask = taskService.moveTaskToStatus(userId, id, Status.valueOf(status.toUpperCase())); // Преобразуем статус в ENUM
        return movedTask;
    }


    @GetMapping("allTask/{id}")
    public List<Task> getAllTask(@PathVariable Long id) {
        return taskService.getAllTask(id);
    }

    @DeleteMapping("/deleteTask/{userId}/{taskId}")
    public Task deleteTask(@PathVariable Long userId, @PathVariable Long taskId) {
        return taskService.deleteTask(userId, taskId);
    }
}

