package the.best.thebestproject.service.users;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import the.best.thebestproject.dto.RegisterUserDto;
import the.best.thebestproject.model.Users;

@Service
public interface UsersService {

    boolean userExistsByEmail(String email);

    Users createNewUser(Users users);

    void updateUser(Users user);

    Users findUserByEmail(String email);

    void isValidPassword(String email, String password);

    String changePassword(Users users, String password);
}
