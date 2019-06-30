package com.jdbctemplate.tests;

import com.yevhensynii.config.ApplicationConfig;
import com.yevhensynii.dao.UserDaoImplementation;
import com.yevhensynii.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {ApplicationConfig.class },
        loader = AnnotationConfigContextLoader.class)
public class UserServiceImplementationTest {
    private UserDaoImplementation userDao;
    private User userExpected;
    private User userActual;

    @Before
    public void setUp() {
        userDao = new UserDaoImplementation();
        userActual = new User();
        userExpected = new User();

        userActual.setAge(22);
        userActual.setName("Alex");
        userDao.create(userActual);

        userActual.setAge(30);
        userActual.setName("Bob");
        userDao.create(userActual);
    }

    @Test
    public void createUser() {
        userActual.setAge(21);
        userActual.setName("Vlad");
        userDao.create(userActual);

        User user = userDao.getById(3L).get();
        userExpected = new User();
        userExpected.setName(user.getName());
        userExpected.setAge(user.getAge());
        assertEquals(userActual, userExpected);
    }

    @Test
    public void updateUser() {
        userExpected.setId(3L);
        userExpected.setAge(40);
        userExpected.setName("Mike");

        userDao.update(userExpected);
        userActual = userDao.getById(3L).get();
        assertEquals(userExpected, userActual);
    }

    @Test
    public void getUserById() {
        userExpected.setId(2L);
        userExpected.setAge(30);
        userExpected.setName("Bob");

        userActual = userDao.getById(2L).get();
        assertEquals(userExpected, userActual);
    }

    @Test
    public void deleteUser() {
        List<User> allUserActual = userDao.getAll();
        userDao.delete(2L);
        assertEquals(1, allUserActual.size());
    }
}
