package kg.attractor.java25.labwork63_edufood.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kg.attractor.java25.labwork63_edufood.dto.RegisterDto;
import kg.attractor.java25.labwork63_edufood.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("auth")
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;


    @GetMapping("login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", true);
            log.warn("Failed login attempt detected");
        }
        return "auth/login";
    }

    @GetMapping("register")
    public String registerPage(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        log.info("Opened registration page");
        return "auth/register";
    }

    @PostMapping("register")
    public String processRegister(@Valid @ModelAttribute RegisterDto registerDto, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            log.warn("Registration validation failed for email: {}", registerDto.getEmail());
            model.addAttribute("errors", result);
            return "auth/register";
        }

        try {
            authService.registerUser(registerDto);
            log.info("User registered successfully with email: {}", registerDto.getEmail());
        } catch (RuntimeException e) {
            log.error("Error during registration for email {}: {}", registerDto.getEmail(), e.getMessage());
            model.addAttribute("registrationError", e.getMessage());
            return "auth/register";
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerDto.getEmail(), registerDto.getPassword());

        try {
            Authentication auth = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(auth);

            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());

            log.info("User authenticated automatically after registration: {}", registerDto.getEmail());
        } catch (Exception ex) {
            log.error("Automatic authentication failed after registration for user {}: {}", registerDto.getEmail(), ex.getMessage());
        }
        return "redirect:/";
    }
}
