package com.yevhensynii.dao;

import com.yevhensynii.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDaoImplementation implements UserDao {
    private static final String CREATE = "INSERT INTO users (name, age) VALUES (?, ?)";
    private static final String READ = "SELECT * FROM users WHERE id = ?";
    private static final String READ_ALL = "SELECT * FROM users";
    private static final String UPDATE = "UPDATE users SET name = ?, age = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";

    private final DataSource dataSource;
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    @Override
    public void create(User user) {
        jdbcTemplate.update(CREATE, user.getName(), user.getAge());
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update(UPDATE, user.getName(), user.getAge(), user.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(READ, new Object[]{id}, new BeanPropertyRowMapper<>(User.class)));
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(READ_ALL, new BeanPropertyRowMapper<>(User.class));
    }
}
