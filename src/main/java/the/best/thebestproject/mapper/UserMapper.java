package the.best.thebestproject.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import the.best.thebestproject.model.Users;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> Users mapToUser(T t) {
        return this.modelMapper.map(t, Users.class);
    }

    public <T> T mapToDto(Users users, Class<T> t) {
        return this.modelMapper.map(users, t);
    }
}
