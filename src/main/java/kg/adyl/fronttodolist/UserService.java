package kg.adyl.fronttodolist;

import kg.adyl.fronttodolist.entity.RegisterEntity;
import kg.adyl.fronttodolist.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public User login(RegisterEntity user) {
        User logined  = userRepo.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        return logined;
    }

    public User register(RegisterEntity user) {
        User registered = new User();
        registered.setUserName(user.getUserName());
        registered.setPassword(user.getPassword());
        userRepo.save(registered);
        return registered;
    }
}
