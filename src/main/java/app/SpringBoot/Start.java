package app.SpringBoot;

import app.SpringBoot.model.Role;
import app.SpringBoot.model.User;
import app.SpringBoot.service.RoleService;
import app.SpringBoot.service.UserService;
import org.springframework.stereotype.Component;
import java.util.HashSet;

@Component
public class Start {

    private final RoleService roleService;

    private final UserService userService;

    public Start(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }


    public void save() {
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");
        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");
        roleService.save(roleUser);
        roleService.save(roleAdmin);


        User user = new User();
        user.setPassword("admin");
        user.setEmail("admin");
        user.setEnabled(true);
        user.setFirstName("Александр");
        user.setLastName("Петров");
        HashSet<Role> role = new HashSet<>();
        role.add(roleAdmin);
        role.add(roleUser);

        user.setRoles(role);
        userService.save(user);

        User user2 = new User();
        user2.setPassword("user");
        user2.setEmail("user");
        user2.setEnabled(true);
        user2.setFirstName("Игорь");
        user2.setLastName("Андреев");
        HashSet<Role> role2 = new HashSet<>();
        role2.add(roleUser);

        user2.setRoles(role2);
        userService.save(user2);

    }

}
