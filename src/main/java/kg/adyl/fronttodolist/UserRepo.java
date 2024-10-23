package kg.adyl.fronttodolist;

import kg.adyl.fronttodolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUserNameAndPassword(String userName, String password);
}
