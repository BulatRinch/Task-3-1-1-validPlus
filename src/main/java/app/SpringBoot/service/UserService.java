package app.SpringBoot.service;

import app.SpringBoot.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.List;


public interface UserService extends UserDetailsService {
    List<User> findAll();

    User find(Long userId) throws NullPointerException;

    void delete(Long userId);

    boolean save(User user);




}
