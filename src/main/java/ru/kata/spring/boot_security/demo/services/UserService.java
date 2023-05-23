package ru.kata.spring.boot_security.demo.services;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> findByUsername(String username);

    public void save(User user);

    public List<User> getAllUsers();

    public User getUserById(long id);

    public void update(User user);

    public void removeById(long id);
}
