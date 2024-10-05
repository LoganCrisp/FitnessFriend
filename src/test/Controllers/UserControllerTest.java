package com.fitnessfriend;

import com.fitnessfriend.entity.User;
import com.fitnessfriend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FitnessFriendApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testAddUser() {
        User user = new User();
        user.setName("Logan");
        user.setEmail("logan@example.com");

        User savedUser = userService.addUser(user);
        assertThat(savedUser.getId()).isNotNull();
    }
}
