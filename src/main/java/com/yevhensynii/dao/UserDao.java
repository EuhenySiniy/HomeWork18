package com.yevhensynii.dao;

import com.yevhensynii.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    void create(User user);

    void update(User user);

    void delete(Long id);

    Optional<User> getById(Long id);

    List<User> getAll();
}
