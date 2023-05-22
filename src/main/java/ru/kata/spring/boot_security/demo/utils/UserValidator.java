package ru.kata.spring.boot_security.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserCRUDService;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserCRUDService userCRUD;

    @Autowired
    public UserValidator(UserCRUDService userCRUD) {
        this.userCRUD = userCRUD;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Optional<User> name = userCRUD.findByUsername(user.getUsername());
        if (name.isPresent())
            errors.rejectValue("username", "1", "Такой login уже существует");
    }
}
