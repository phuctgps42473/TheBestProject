package the.best.thebestproject.service.users;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import the.best.thebestproject.dto.RegisterUserDto;
import the.best.thebestproject.model.Users;

@Service
public interface UsersService {

    Users createNewUser(RegisterUserDto dto);

    Users findUserByEmail(String email);

    void isValidPassword(String email, String password);
}
