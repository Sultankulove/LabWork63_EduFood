package kg.attractor.java25.labwork63_edufood.service;

import kg.attractor.java25.labwork63_edufood.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String username);
}
