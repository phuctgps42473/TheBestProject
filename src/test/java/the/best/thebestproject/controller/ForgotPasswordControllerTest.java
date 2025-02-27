package the.best.thebestproject.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import the.best.thebestproject.model.Users;
import the.best.thebestproject.service.CookieService.CookieServiceImpl;
import the.best.thebestproject.service.users.UsersServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ForgotPasswordControllerTest {

    @Mock
    private UsersServiceImpl usersService;

    @Mock
    private CookieServiceImpl cookieService;

    @Mock
    private HttpSession session;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private ForgotPasswordController forgotPasswordController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testForgotPasswordPage() {
        String viewName = forgotPasswordController.forgotPasswordPage();
        assertEquals("forgot-password", viewName);
    }

    @Test
    void testHandleForgotPassword_UserNotFound() {
        Model model = new BindingAwareModelMap();
        when(usersService.findUserByEmail("test@example.com")).thenThrow(new RuntimeException());

        String viewName = forgotPasswordController.handleForgotPassword(model, "test@example.com");
        assertEquals("forgot-password", viewName);
        assertEquals("Không có tài khoản email này", model.getAttribute("emailError"));
    }

    @Test
    void testHandleForgotPassword_Success() {
        Model model = new BindingAwareModelMap();
        Users user = new Users();
        user.setEmail("test@example.com");

        when(usersService.findUserByEmail("test@example.com")).thenReturn(user);

        String viewName = forgotPasswordController.handleForgotPassword(model, "test@example.com");
        assertEquals("redirect:reset-token", viewName);
        verify(cookieService, times(1)).addCookie(any(Cookie.class));
    }

    @Test
    void testResetTokenPage() {
        String viewName = forgotPasswordController.resetTokenPage();
        assertEquals("reset-token", viewName);
    }

    @Test
    void testHandleResetToken_InvalidToken() {
        when(cookieService.getCookie("reset-email")).thenReturn(new Cookie("reset-email", "test@example.com"));
        Users user = new Users();
        user.setResetToken("123456");
        when(usersService.findUserByEmail("test@example.com")).thenReturn(user);

        String viewName = forgotPasswordController.handleResetToken("wrongToken", session, redirectAttributes);
        assertEquals("reset-token", viewName);
    }

    @Test
    void testHandleResetToken_ValidToken() {
        when(cookieService.getCookie("reset-email")).thenReturn(new Cookie("reset-email", "test@example.com"));
        Users user = new Users();
        user.setResetToken("123456");
        when(usersService.findUserByEmail("test@example.com")).thenReturn(user);

        String viewName = forgotPasswordController.handleResetToken("123456", session, redirectAttributes);
        assertEquals("redirect:reset-password", viewName);
    }

    @Test
    void testResetPasswordPage_NoCSRF() {
        when(session.getAttribute("csrf")).thenReturn(null);
        String viewName = forgotPasswordController.resetPasswordPage(session);
        assertEquals("redirect:login", viewName);
    }

    @Test
    void testHandleResetPassword_PasswordMismatch() {
        Model model = new BindingAwareModelMap();
        when(session.getAttribute("csrf")).thenReturn("valid_csrf");

        String viewName = forgotPasswordController.handleResetPassword(model, session, "valid_csrf", "pass1", "pass2");
        assertEquals("reset-password", viewName);
        assertEquals("Passwords does not match", model.getAttribute("errorPassword"));
    }

    @Test
    void testHandleResetPassword_Success() {
        Model model = new BindingAwareModelMap();
        when(session.getAttribute("csrf")).thenReturn("valid_csrf");
        when(cookieService.getCookie("reset-email")).thenReturn(new Cookie("reset-email", "test@example.com"));
        Users user = new Users();
        when(usersService.findUserByEmail("test@example.com")).thenReturn(user);

        String viewName = forgotPasswordController.handleResetPassword(model, session, "valid_csrf", "newPass", "newPass");
        assertEquals("redirect:login", viewName);
    }
}