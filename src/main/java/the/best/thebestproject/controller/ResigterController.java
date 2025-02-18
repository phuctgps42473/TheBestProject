package the.best.thebestproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResigterController {
    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

}
