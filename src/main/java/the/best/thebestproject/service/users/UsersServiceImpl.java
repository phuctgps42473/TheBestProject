package the.best.thebestproject.service.users;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import the.best.thebestproject.dto.RegisterUserDto;
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
        Users users;
        users = this.findUserByEmail(dto.getEmail());
        if (users != null) throw new IllegalArgumentException("user existed");

        users = userMapper.mapToUser(dto);
        return usersRepository.save(users);
    }

    @Override
    public Users findUserByEmail(String email) {
        return usersRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public void isValidPassword(String email, String password) {

    }
}
