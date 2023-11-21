package com.example.btl;

import com.example.btl.entity.User;
import com.example.btl.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestAuthen {
    @Autowired
    private  UserRepository userRepository ;

    @Test
    public void testAuthen() {
        User user = userRepository.findByUsername("admin").get();
        System.out.println(user.getPassword().length());
        System.out.println(BCrypt.checkpw("admin",user.getPassword()));
    }
}
