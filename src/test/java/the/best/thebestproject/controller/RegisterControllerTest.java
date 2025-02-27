package the.best.thebestproject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import the.best.thebestproject.dto.RegisterUserDto;
import the.best.thebestproject.model.Users;
import the.best.thebestproject.service.CookieService.CookieService;
import the.best.thebestproject.service.users.UsersService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegisterControllerTest {

    @Mock
    private UsersService usersService;

    @Mock
    private CookieService cookieService;

    @InjectMocks
    private ResigterController registerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRegisterPage() {
        RegisterUserDto dto = new RegisterUserDto();
        String viewName = registerController.getRegisterPage(dto);
        assertEquals("register", viewName);
    }

    @Test
    void testRegister_PasswordMismatch() {
        Model model = new BindingAwareModelMap();
        RegisterUserDto dto = new RegisterUserDto();
        dto.setPassword("password123");
        String viewName = registerController.register(model, dto, "wrongPassword");

        assertEquals("register", viewName);
        assertEquals("Mật khẩu không khớp", model.getAttribute("passwordError"));
    }

    @Test
    void testRegister_EmailAlreadyExists() {
        Model model = new BindingAwareModelMap();
        RegisterUserDto dto = new RegisterUserDto();
        dto.setPassword("password123");
        dto.setEmail("test@example.com");

        when(usersService.userExistsByEmail(dto.getEmail())).thenReturn(true);

        String viewName = registerController.register(model, dto, "password123");

        assertEquals("register", viewName);
        assertEquals("Tài khoản email đã được đăng ký", model.getAttribute("emailError"));
    }

    @Test
    void testRegister_SuccessfulRegistration() {
        Model model = new BindingAwareModelMap();
        RegisterUserDto dto = new RegisterUserDto();
        dto.setFullName("John Doe");
        dto.setEmail("john@example.com");
        dto.setPassword("password123");

        when(usersService.userExistsByEmail(dto.getEmail())).thenReturn(false);

        Users mockUser = Users.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .id("12345")
                .build();

        when(usersService.createNewUser(any(Users.class))).thenReturn(mockUser);

        String viewName = registerController.register(model, dto, "password123");

        assertEquals("redirect:login", viewName);
        verify(cookieService, times(1)).addCookie(any());
    }
}
