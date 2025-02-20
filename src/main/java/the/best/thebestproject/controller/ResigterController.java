package the.best.thebestproject.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import the.best.thebestproject.dto.RegisterUserDto;
import the.best.thebestproject.model.Users;
import the.best.thebestproject.service.users.UsersService;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ResigterController {


    private final UsersService usersService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUserDto", new RegisterUserDto());
        return "register";
    }

    @PostMapping("/register")
    public String getLoginPage(@ModelAttribute RegisterUserDto dto, Model model) {
        try {

            Users users = usersService.createNewUser(dto);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "register";

        }

    }
}
