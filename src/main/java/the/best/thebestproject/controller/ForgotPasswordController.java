package the.best.thebestproject.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import the.best.thebestproject.model.Users;
import the.best.thebestproject.service.CookieService.CookieServiceImpl;
import the.best.thebestproject.service.users.UsersServiceImpl;

@Controller
public class ForgotPasswordController {
    private final UsersServiceImpl usersService;
    private final CookieServiceImpl cookieService;

    public ForgotPasswordController(UsersServiceImpl usersService, CookieServiceImpl cookieService) {
        this.usersService = usersService;
        this.cookieService = cookieService;
    }


    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String handleForgotPassword(
            Model model,
            @RequestParam(name = "email") String email
    ) {
        try {
            Users users = this.usersService.findUserByEmail(email);
            String resetToken = "123456";

            users.setResetToken(resetToken);
            usersService.updateUser(users);

            cookieService.addCookie(new Cookie("reset-email", users.getEmail()));
            return "redirect:reset-token";
        } catch (RuntimeException ex) {
            model.addAttribute("emailError", "Không có tài khoản email này");
            return "forgot-password";
        }
    }

    @GetMapping("/reset-token")
    public String resetTokenPage() {
        return "reset-token";
    }

    @PostMapping("/reset-token")
    public String handleResetToken(
            @RequestParam(name = "token") String token,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        Cookie cookie = cookieService.getCookie("reset-email");
        String email = cookie.getValue();
        if (email == null) {
            return "redirect:login";
        }

        try {
            Users user = this.usersService.findUserByEmail(email);
            if (user.getResetToken().equals(token)) {
                String csrf = "hehehe";
                session.setAttribute("csrf", csrf);
                redirectAttributes.addAttribute("csrf", csrf);
                return "redirect:reset-password";
            } else {
                return "reset-token";
            }
        } catch (RuntimeException ex) {
            return "redirect:login";
        }
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage(
            HttpSession session
    ) {
        Object csrfObject = session.getAttribute("csrf");
        if (csrfObject == null) {
            return "redirect:login";
        }
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String handleResetPassword(
            Model model,
            HttpSession session,
            @RequestParam(name = "csrf") String csrf,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "confirmPassword") String confirmPassword
    ) {
        Object csrfObject = session.getAttribute("csrf");
        if (csrfObject == null) {
            return "redirect:login";
        }

        if (!csrf.equals(csrfObject.toString())) {
            return "redirect:login";
        }

        if (password.equals(confirmPassword)) {
            Users user = usersService.findUserByEmail(cookieService.getCookie("reset-email").getValue());
            user.setPassword(usersService.changePassword(user, password));
            usersService.updateUser(user);

            return "redirect:login";
        } else {
            model.addAttribute("errorPassword", "Passwords does not match");
            return "reset-password";
        }
    }
}
