package kg.attractor.java25.labwork63_edufood.service.impl;

import kg.attractor.java25.labwork63_edufood.config.ApplicationConfig;
import kg.attractor.java25.labwork63_edufood.dto.RegisterDto;
import kg.attractor.java25.labwork63_edufood.model.User;
import kg.attractor.java25.labwork63_edufood.repo.UserRepo;
import kg.attractor.java25.labwork63_edufood.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepo repo;
    private final ApplicationConfig config;

    @Override
    public void registerUser(RegisterDto register) {
        log.info("Попытка регистрации пользователя с email: {}", register.getEmail());
        if (repo.existsByEmail((register.getEmail()))) {
            log.warn("Регистрация отклонена: email {} уже используется", register.getEmail());
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
        log.info("Успешно зарегистрирован пользователь с email: {}", register.getEmail());
    }
}
