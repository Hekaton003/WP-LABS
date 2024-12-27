package com.example.exercise_one;

import com.example.exercise_one.model.User;
import com.example.exercise_one.model.enums.Role;
import com.example.exercise_one.model.exp.InvalidArgumentsException;
import com.example.exercise_one.model.exp.PasswordsDoNotMatchException;
import com.example.exercise_one.model.exp.UserException;
import com.example.exercise_one.repository.jpa.UserRepository;
import com.example.exercise_one.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RegisterTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        User user = new User("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        this.userService = Mockito.spy(new UserServiceImpl(this.userRepository, this.passwordEncoder));
    }

    @Test
    public  void RegisterSuccess() {
        User user = this.userService.register("username", "password", "password", "name","surname",Role.ROLE_USER);
        Mockito.verify(this.userService).register("username", "password", "password", "name","surname",Role.ROLE_USER);
        Assert.assertNotNull("User is null",user);
        Assert.assertEquals("Username does not match", "username", user.getUsername());
        Assert.assertEquals("Name does not match", "name", user.getName());
        Assert.assertEquals("Surname does not match", "surname", user.getSurname());
        Assert.assertEquals("Role does not match", Role.ROLE_USER, user.getRole());
        Assert.assertEquals("Password does not match", "password", user.getPassword());
    }

    @Test
    public void testNullUsername() {
        String username = null;
        String password = "password";
        Assert.assertThrows("InvalidArgException expected", InvalidArgumentsException.class, () -> {
            User user = this.userService.register(username, password, password, "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register(username, password, password, "Name", "Surname", Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        String password = "password";
        Assert.assertThrows("InvalidArgException expected", InvalidArgumentsException.class, () -> {
            User user = this.userService.register(username, password, password, "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register(username, password, password, "Name", "Surname", Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgException expected", InvalidArgumentsException.class, () -> {
            User user = this.userService.register(username, password, password, "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register(username, password, password, "Name", "Surname", Role.ROLE_USER);
    }

    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgException expected", InvalidArgumentsException.class, () -> {
            User user = this.userService.register(username, password, password, "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register(username, password, password, "Name", "Surname", Role.ROLE_USER);
    }

    @Test
    public void testPasswordsDoNotMatch() {
        String password = "password";
        String passwordConfirm = "otherPassword";

        Assert.assertThrows("PasswordsDoNotMatchException expected", PasswordsDoNotMatchException.class, () -> {
            User user = this.userService.register("username", password, passwordConfirm, "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register("username", password, passwordConfirm, "Name", "Surname", Role.ROLE_USER);
    }

    @Test
    public void testUsernameAlreadyExists() {
        User existingUser = new User("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.userRepository.findByUsername("username")).thenReturn(Optional.of(existingUser));

        Assert.assertThrows("UsernameAlreadyExistsException expected", UserException.class, () -> {
            User user = this.userService.register("username", "password", "password", "Name", "Surname", Role.ROLE_USER);
        });

        Mockito.verify(this.userService).register("username", "password", "password", "Name", "Surname", Role.ROLE_USER);
    }

}
