package kg.attractor.java25.labwork63_edufood.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
    @GetMapping("login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("login")
    public String postLogin() {

        return "login";
    }

    @GetMapping("register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("register")
    public String postRegister() {
        return "register";
    }


}
