package app.SpringBoot.service;

import app.SpringBoot.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {
    List<Role> findAllRoles();
    void save(Role role);
}
