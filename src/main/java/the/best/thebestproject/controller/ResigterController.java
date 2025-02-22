package the.best.thebestproject.controller;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import the.best.thebestproject.dto.RegisterUserDto;
import the.best.thebestproject.model.Users;
import the.best.thebestproject.service.CookieService.CookieService;
import the.best.thebestproject.service.users.UsersServiceImpl;

@Controller
public class ResigterController {
    private final UsersServiceImpl usersService;
    private CookieService cookieService;

    public ResigterController(UsersServiceImpl usersService, CookieService cookieService) {
        this.usersService = usersService;
        this.cookieService = cookieService;
    }


    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute(name = "dto") RegisterUserDto dto) {
        return "register";
    }

    @PostMapping("/register")
    public String register(
            Model model,
            @ModelAttribute("dto") RegisterUserDto dto,
            @RequestParam(name = "confirmPassword") String confirmPassword
    ) {
        if (dto.getPassword().compareTo(confirmPassword) != 0) {
            model.addAttribute("passwordError", "Mật khẩu không khớp");
            return "register";
        }

        if (this.usersService.userExistsByEmail(dto.getEmail())) {
            model.addAttribute("emailError", "Tài khoản email đã được đăng ký");
            return "register";
        } else {
            Users user  = Users.builder().name(dto.getFullName()).email(dto.getEmail()).password(dto.getPassword()).build();
            this.usersService.createNewUser(user);

            Cookie cookie = new Cookie("id", user.getId());
            this.cookieService.addCookie(cookie);

            return "redirect:login";
        }
    }
}
