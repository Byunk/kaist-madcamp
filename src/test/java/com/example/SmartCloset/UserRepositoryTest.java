package com.example.SmartCloset;

import com.example.SmartCloset.model.User;
import com.example.SmartCloset.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;

import java.util.List;
import java.util.Optional;

public class UserRepositoryTest {

    UserRepository repository = new UserRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

}
