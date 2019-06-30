package com.yevhensynii.controller;

import com.yevhensynii.exceptions.NotFoundEntityException;
import com.yevhensynii.model.User;
import com.yevhensynii.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @RequestMapping(method = GET)
    public String getUser(Long id) throws NotFoundEntityException {
        User user = userService.getById(id).orElseThrow(() -> new NotFoundEntityException(id));
        return user.toString();
    }

    @RequestMapping(method = GET)
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @RequestMapping(method = POST)
    public String createUser(User user) {
        userService.create(user);
        return "user created";
    }

    @RequestMapping(method = POST)
    public String updateUser(User user) {
        userService.update(user);
        return "user updated";
    }

    @RequestMapping(method = DELETE)
    public String deleteUser(Long id) {
        userService.delete(id);
        return "User " + id + " deleted";
    }
}
