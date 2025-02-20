package the.best.thebestproject.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import the.best.thebestproject.dto.RegisterUserDto;
import the.best.thebestproject.model.Users;
import the.best.thebestproject.service.users.UsersService;

@Controller
public class LoginController {
@GetMapping("/login")
    public String login(){
    return "login";
}
}
