package kg.adyl.fronttodolist;

import kg.adyl.fronttodolist.entity.Status;
import kg.adyl.fronttodolist.entity.Task;
import kg.adyl.fronttodolist.entity.TaskDto;
import kg.adyl.fronttodolist.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepo taskRepo;
    private final UserRepo userRepo;

    public Task addTask(Long userId, TaskDto dto) {
        Task task = new Task();
        task.setText(dto.getText());
        task.setStatus(Status.TODO);
        User user = userRepo.findById(userId).orElseThrow(RuntimeException::new);
        user.getTasks().add(task);
        return taskRepo.saveAndFlush(task);
    }

    public Task editTask(Long userId, Long id, TaskDto dto) {
        User user = userRepo.findById(userId).orElseThrow();
        List<Task> tasks = user.getTasks();
        for(Task task : tasks) {
            if(task.getId().equals(id)) {
                task.setText(dto.getText());
                return taskRepo.saveAndFlush(task);
            }
        }
        throw new RuntimeException("Task not found");
    }

    public List<Task> getAllTask(Long id) {
        User user = userRepo.findById(id).orElseThrow();
        return user.getTasks();
    }


    public Task moveTaskToStatus(Long userId, Long taskId, Status status) {
        User user = userRepo.findById(userId).orElseThrow();
        Task edited = user.getTasks().stream().filter(task -> task.getId().equals(taskId)).findFirst().orElseThrow();
        edited.setStatus(status);
        return taskRepo.save(edited);
    }

    public Task deleteTask(Long userId, Long taskId) {
        User user = userRepo.findById(userId).orElseThrow();
        Task edited = user.getTasks().stream().filter(task -> task.getId().equals(taskId)).findFirst().orElseThrow();
        user.getTasks().remove(edited);
        taskRepo.delete(edited);
        userRepo.save(user);
        return edited;
    }
}
