package the.best.thebestproject.service.users;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import the.best.thebestproject.dto.RegisterUserDto;
import the.best.thebestproject.dto.request.ApiResponse;
import the.best.thebestproject.mapper.UserMapper;
import the.best.thebestproject.model.Users;
import the.best.thebestproject.repository.UsersRepository;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {


    private final UserMapper userMapper;
    private final UsersRepository usersRepository;

    @Override
    public Users createNewUser(RegisterUserDto dto) {
        Users users = userMapper.mapToUser(dto);
        if (users != null) throw new RuntimeException("User is null");
        return usersRepository.save(userMapper.mapToUser(dto));
    }

    @Override
    public Users findUserByEmail(String email) {
        return null;
    }

    @Override
    public void isValidPassword(String email, String password) {

    }
}
