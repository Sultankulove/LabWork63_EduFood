package kg.attractor.java25.labwork63_edufood.service.impl;

import kg.attractor.java25.labwork63_edufood.model.User;
import kg.attractor.java25.labwork63_edufood.repo.UserRepo;
import kg.attractor.java25.labwork63_edufood.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("Поиск пользователя по email: {}", email);

        Optional<User> userOpt = userRepo.findByEmail(email);

        if (userOpt.isEmpty()) {
            log.warn("Пользователь с email '{}' не найден", email);
        } else {
            log.debug("Пользователь найден: id={}, username={}", userOpt.get().getId(), userOpt.get().getUsername());
        }

        return userOpt;
    }
}
