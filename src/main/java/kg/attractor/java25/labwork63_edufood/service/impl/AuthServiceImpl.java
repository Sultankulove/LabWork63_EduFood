package kg.attractor.java25.labwork63_edufood.service.impl;

import kg.attractor.java25.labwork63_edufood.config.ApplicationConfig;
import kg.attractor.java25.labwork63_edufood.dto.RegisterDto;
import kg.attractor.java25.labwork63_edufood.model.User;
import kg.attractor.java25.labwork63_edufood.repo.UserRepo;
import kg.attractor.java25.labwork63_edufood.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo repo;
    private final ApplicationConfig config;

    @Override
    public void registerUser(RegisterDto register) {
        if (repo.existsByEmail((register.getEmail()))) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        User user = new User();
        user.setId(null);
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(config.passwordEncoder().encode(register.getPassword()));
        user.setEnabled(true);
        user.setRole("ROLE_USER");
        repo.save(user);
    }
}
